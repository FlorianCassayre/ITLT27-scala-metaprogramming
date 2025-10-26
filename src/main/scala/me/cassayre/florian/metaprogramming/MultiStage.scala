package me.cassayre.florian.metaprogramming

import scala.quoted.*
import scala.quoted.staging.Compiler
import scala.quoted.staging.Compiler.Settings

object MultiStage:

  def helloWorld(n: Int): String =
    if n == 0 then
      "Hello world!"
    else
      given Settings = Settings.make(compilerArgs=List("-experimental"))
      given Compiler = Compiler.make(getClass.getClassLoader)
      staging.run:
        '{ helloWorld(${Expr(n)} - 1) }
