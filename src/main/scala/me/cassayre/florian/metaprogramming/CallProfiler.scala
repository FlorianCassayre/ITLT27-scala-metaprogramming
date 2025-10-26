package me.cassayre.florian.metaprogramming

import scala.quoted.{Expr, Quotes, Type}

object CallProfiler:

  inline def timed[R](inline expr: R): R = ${ timedImpl('expr) }

  private def timedImpl[R: Type](expr: Expr[R])(using Quotes): Expr[R] = '{
    val t0 = System.nanoTime()
    val result = $expr
    val t1 = System.nanoTime()
    val code = ${Expr(expr.show)}
    println(f"$code: ${(t1 - t0) / 1e9}%.6f ms")
    result
  }
