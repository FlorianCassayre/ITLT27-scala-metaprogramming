package me.cassayre.florian.metaprogramming

import me.cassayre.florian.metaprogramming.JustInTime.*
import org.scalatest.funsuite.AnyFunSuite
import scala.quoted.*
import scala.quoted.staging.Compiler
import scala.quoted.staging.Compiler.Settings

class JustInTimeSpec extends AnyFunSuite:

  test("successfully prints 'Hello World!'"):
    given Settings = Settings.make(compilerArgs = List("-experimental"))
    given Compiler = Compiler.make(getClass.getClassLoader)
    val result =
      staging.run:
        // https://esolangs.org/wiki/Brainfuck#Hello,_World!
        val program = "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++."
        compile(program, 1000)
    assert("Hello World!\n" === result)
