package me.cassayre.florian.metaprogramming

import me.cassayre.florian.metaprogramming.SingletonType.*
import org.scalatest.funsuite.AnyFunSuite

import scala.compiletime.constValue

class SingletonTypeSpec extends AnyFunSuite:

  test("summons the correct GCD result"):
    assert(6 === constValue[Gcd[48, 18]])
