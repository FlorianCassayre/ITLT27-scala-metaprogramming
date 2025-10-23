package me.cassayre.florian.metaprogramming

import scala.compiletime.ops.int.*

object SingletonType:

  type Gcd[A <: Int, B <: Int] =
    B match
      case 0 => A
      case _ => Gcd[B, A % B]
