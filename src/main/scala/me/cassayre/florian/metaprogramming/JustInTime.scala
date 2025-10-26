package me.cassayre.florian.metaprogramming

import java.io.StringWriter
import scala.quoted.*

object JustInTime:

  class Context(private var pointer: Int, tape: Array[Byte], printer: StringWriter):
    inline def add(value: Byte): Unit = tape(pointer) = (tape(pointer) + value).toByte
    inline def move(offset: Int): Unit = pointer += offset
    inline def print(): Unit = printer.append(tape(pointer).toChar)
    inline def peek(): Byte = tape(pointer)
    inline override def toString: String = printer.toString
  object Context:
    inline def apply(tapeSize: Int): Context = new Context(500, Array.ofDim[Byte](tapeSize), new StringWriter())

  def parse(program: List[Char], ctx: Expr[Context])(using Quotes): Expr[Unit] =
    def loop(prog: List[Char]): (List[Expr[Unit]], List[Char]) =
      prog match
        case Nil => (Nil, Nil)
        case head :: tail =>
          def prepend(expr: Expr[Unit]): (List[Expr[Unit]], List[Char]) =
            val (restCode, restProg) = loop(tail)
            (expr :: restCode, restProg)
          head match
            case '+' => prepend('{ $ctx.add(1) })
            case '-' => prepend('{ $ctx.add(-1) })
            case '>' => prepend('{ $ctx.move(1) })
            case '<' => prepend('{ $ctx.move(-1) })
            case '.' => prepend('{ $ctx.print() })
            case '[' =>
              val (body, rest) = loop(tail)
              val loopExpr = '{ while $ctx.peek() > 0 do ${Expr.block(body, '{})} }
              val (restCode, restProg) = loop(rest)
              (loopExpr :: restCode, restProg)
            case ']' => (Nil, tail)

    Expr.block(loop(program)._1, '{})

  def compile(program: String, tapeSize: Int)(using Quotes): Expr[String] = '{
    val ctx = Context(${Expr(tapeSize)})
    ${parse(program.toList, 'ctx)}
    ctx.toString
  }
