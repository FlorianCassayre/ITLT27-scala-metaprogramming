package me.cassayre.florian.metaprogramming

import me.cassayre.florian.metaprogramming.InlineFunAdvanced.*
import me.cassayre.florian.metaprogramming.test.Assert.*
import org.scalatest.funsuite.AnyFunSuite

class InlineFunAdvancedSpec extends AnyFunSuite:

  test("adds correctly"):
    assert(3 === add(1, 2))

  test("inlines correctly"):
    assertExprEquals(3, add(1, 2))

  test("inlines two levels"):
    assertExprEquals(6, add(add(1, 2), 3))
