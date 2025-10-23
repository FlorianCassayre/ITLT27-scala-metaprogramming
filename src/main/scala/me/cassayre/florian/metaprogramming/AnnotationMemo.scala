package me.cassayre.florian.metaprogramming

import scala.annotation.*
import scala.collection.mutable.HashMap
import scala.quoted.*

// Inspired by https://github.com/scala/scala3/pull/16392
object AnnotationMemo:

  class memo extends MacroAnnotation:
    override def transform(using Quotes)(definition: quotes.reflect.Definition, companion: Option[quotes.reflect.Definition]): List[quotes.reflect.Definition] =
      import quotes.reflect.*
      definition match
        case DefDef(name, List(TermParamClause(params)), tpt, Some(body)) =>
          val keyExpr = Expr.ofTupleFromSeq(params.map(p => Ref(p.symbol).asExpr))
          (keyExpr, body.asExpr) match
            case ('{ $key: keyType }, '{ $body: bodyType }) =>
              val cacheType = TypeRepr.of[HashMap[keyType, bodyType]]
              val cacheSymbol = Symbol.newVal(definition.symbol.owner, name, cacheType, Flags.Private, Symbol.noSymbol)
              val cacheVal = ValDef(cacheSymbol, Some('{ HashMap[keyType, bodyType]() }.asTerm))
              val newBody = '{ ${ Ref(cacheSymbol).asExprOf[HashMap[keyType, bodyType]] }.getOrElseUpdate($key, $body) }.asTerm
              val newDef = DefDef.copy(definition)(name, List(TermParamClause(params)), tpt, Some(newBody))
              List(cacheVal, newDef)
        case _ => report.errorAndAbort("@memo cannot be used in this context")
