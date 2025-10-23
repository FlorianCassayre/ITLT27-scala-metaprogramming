package me.cassayre.florian.metaprogramming

import me.cassayre.florian.metaprogramming.AnnotationMemo.*
import org.scalatest.funsuite.AnyFunSuite

import scala.compiletime.testing.*

class AnnotationMemoSpec extends AnyFunSuite:

  @memo
  def fibonacci(n: Int): Int =
    if n < 2 then n else fibonacci(n - 2) + fibonacci(n - 1)

  test("fibonacci is correctly implemented"):
    lazy val baseline: LazyList[Int] = 0 #:: 1 #:: baseline.zip(baseline.tail).map(_ + _)
    (0 to 10).foreach(n => assert(
      baseline(n) === fibonacci(n),
      s"Fibonacci #$n does not match the baseline"
    ))

  test("fibonacci does not computationally explode"):
    fibonacci(1000) // Would time out if it was not memoized

  test("does not compile when used on class"):
    val code = "@memo class A"
    val result = typeCheckErrors(code)
    val messages = result.map(_.message)
    assert(messages.contains("@memo cannot be used in this context"))
