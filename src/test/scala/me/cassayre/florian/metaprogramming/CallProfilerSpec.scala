package me.cassayre.florian.metaprogramming

import me.cassayre.florian.metaprogramming.CallProfiler.*
import org.scalatest.funsuite.AnyFunSuite

import java.io.ByteArrayOutputStream
import java.nio.charset.StandardCharsets

class CallProfilerSpec extends AnyFunSuite:

  def sleep(ms: Long): Unit =
    Thread.sleep(ms)

  test("prints the function name and the time"):
    val stream = new ByteArrayOutputStream()
    Console.withOut(stream):
      timed:
        sleep(100)
    val result = new String(stream.toByteArray, StandardCharsets.UTF_8)
    result match
      case s"CallProfilerSpec.this.sleep(100L): $_ ms\n" => ()
      case _ => assert(false, result)
