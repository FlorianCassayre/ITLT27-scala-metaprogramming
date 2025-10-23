package me.cassayre.florian.metaprogramming.test

import scala.quoted.{Expr, Quotes}

object Assert:

  inline def assertExprEquals[A](inline expected: A, inline actual: A): Unit =
    ${ assertExprCompareImpl('expected, 'actual, true) }

  inline def assertExprNotEquals[A](inline expected: A, inline actual: A): Unit =
    ${ assertExprCompareImpl('expected, 'actual, false) }

  private def assertExprCompareImpl[A](expected: Expr[A], actual: Expr[A], shouldEqual: Boolean)(using Quotes): Expr[Unit] =
    def normalize(s: String): String = s match
      case s"($v: $t)" => v
      case _ => s
    val (expectedStr, actualStr) = (normalize(expected.show), normalize(actual.show))
    if shouldEqual == (expectedStr == actualStr) then
      '{ () }
    else
      val message =
        if shouldEqual
        then '{ "Expected '" + ${ Expr(expectedStr) } + "' but got '" + ${ Expr(actualStr) } + "'" }
        else '{ "Expected '" + ${ Expr(expectedStr) } + "' to not equal '" + ${ Expr(actualStr) } + "'" }
      '{ assert(false, $message) }
