package me.cassayre.florian.metaprogramming

import me.cassayre.florian.metaprogramming.InlineStringContext.*
import org.scalatest.funsuite.AnyFunSuite

class InlineStringContextSpec extends AnyFunSuite:

  test("valid URL is parsed"):
    assert("http://example.com" === url"http://example.com".toURI.toASCIIString)
