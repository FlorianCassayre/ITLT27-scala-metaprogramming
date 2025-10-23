package me.cassayre.florian.metaprogramming

import me.cassayre.florian.metaprogramming.InlineFunRec.*
import me.cassayre.florian.metaprogramming.test.Assert.*
import org.scalatest.funsuite.AnyFunSuite

class InlineFunRecSpec extends AnyFunSuite:

  test("computes factorial correctly"):
    assert(24 === factorial(4))

  test("inlines correctly"):
    assertExprEquals(24, factorial(4))
