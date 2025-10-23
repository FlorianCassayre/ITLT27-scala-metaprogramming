package me.cassayre.florian.metaprogramming

object InlineFunAdvancedTransparent:

  transparent inline def add(inline a: Int, inline b: Int): Int = a + b
