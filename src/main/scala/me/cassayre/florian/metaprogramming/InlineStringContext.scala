package me.cassayre.florian.metaprogramming

import java.net.URL
import scala.quoted.*
import scala.util.{Failure, Success, Try}

object InlineStringContext:
  
  extension (inline sc: StringContext)
    inline def url(inline args: Any*): URL = ${ urlImpl('sc, 'args) }
  
  private def urlImpl(scExpr: Expr[StringContext], argsExpr: Expr[Seq[Any]])(using Quotes): Expr[URL] =
    val parts = scExpr.valueOrAbort.parts
    val rawUrl = parts.mkString
    Try(URL(rawUrl)) match
      case Success(_) => '{ URL(${ Expr(rawUrl) }) }
      case Failure(_) => quotes.reflect.report.errorAndAbort(s"Invalid URL: '$rawUrl'")
