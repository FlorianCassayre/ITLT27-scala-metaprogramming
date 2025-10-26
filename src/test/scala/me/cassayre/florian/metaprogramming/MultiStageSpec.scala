package me.cassayre.florian.metaprogramming

import me.cassayre.florian.metaprogramming.MultiStage.*
import org.scalatest.funsuite.AnyFunSuite

class MultiStageSpec extends AnyFunSuite:

  test("returns expected result after some steps"):
    assert("Hello world!" === helloWorld(3))
