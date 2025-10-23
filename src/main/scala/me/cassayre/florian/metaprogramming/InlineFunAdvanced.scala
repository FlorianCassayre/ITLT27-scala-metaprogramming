package me.cassayre.florian.metaprogramming

object InlineFunAdvanced:

  inline def add(inline a: Int, inline b: Int): Int = a + b
