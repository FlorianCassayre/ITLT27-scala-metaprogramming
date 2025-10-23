package me.cassayre.florian.metaprogramming

import me.cassayre.florian.metaprogramming.InlineFun.*
import me.cassayre.florian.metaprogramming.test.Assert.*
import org.scalatest.funsuite.AnyFunSuite

class InlineFunSpec extends AnyFunSuite:

  test("adds correctly"):
    assert(3 === add(1, 2))

  test("inlines correctly"):
    assertExprEquals(3, add(1, 2))

  test("inlines only one level"):
    assertExprNotEquals(6, add(add(1, 2), 3))
