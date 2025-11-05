package me.cassayre.florian.metaprogramming

import scala.quoted.*

object Assert:

  inline def assert(inline condition: Boolean): Unit =
    ${ assertImpl('condition) }

  private def assertImpl(condition: Expr[Boolean])(using Quotes): Expr[Unit] =
    '{ if !$condition then throw AssertionError(${Expr(condition.show)}) }
