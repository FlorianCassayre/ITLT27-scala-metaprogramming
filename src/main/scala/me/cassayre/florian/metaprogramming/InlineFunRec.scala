package me.cassayre.florian.metaprogramming

object InlineFunRec:

  inline def factorial(n: Int): Int =
    if n == 0 then 1
    else n * factorial(n - 1)
