package me.cassayre.florian.metaprogramming

import me.cassayre.florian.metaprogramming.SingletonType.*
import me.cassayre.florian.metaprogramming.test.Assert.*
import org.scalatest.funsuite.AnyFunSuite

class SingletonTypeSpec extends AnyFunSuite:

  test("summons the correct GCD result"):
    assert(6 === summonValue[Gcd[48, 18]])
