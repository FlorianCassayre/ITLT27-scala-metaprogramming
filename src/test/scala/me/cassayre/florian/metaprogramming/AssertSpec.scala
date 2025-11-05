package me.cassayre.florian.metaprogramming

import org.scalatest.funsuite.AnyFunSuite

class AssertSpec extends AnyFunSuite:

  test("assert passes when condition is true"):
    Assert.assert(true)

  test("assert throws when condition is false"):
    val e = intercept[AssertionError]:
      Assert.assert("foo".equals("bar"))
    assert(e.getMessage === """"foo".equals("bar")""")
