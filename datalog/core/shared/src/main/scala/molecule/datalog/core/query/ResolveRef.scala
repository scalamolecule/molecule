package molecule.datalog.core.query

import java.lang.{Long => jLong}
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.datalog.core.query.casting.NestOpt_


trait ResolveRef[Tpl] { self: Base with NestOpt_[Tpl] =>

  protected def resolveRef(es: List[Var], ref: Ref): List[Var] = {
    val (e, refAttr, refId) = (es.last, s":${ref.ns}/${ref.refAttr}", vv)
    if (ref.bidirectional) {
      where += s"(rule$e $e $refId)" -> wClause
      rules += s"[(rule$e $e $refId) [$e $refAttr $refId]]"
      rules += s"[(rule$e $e $refId) [$refId $refAttr $e]]"
    } else {
      where += s"[$e $refAttr $refId]" -> wClause
    }
    es :+ refId
  }

  protected def resolveNestedRef(es: List[Var], ref: Ref): List[Var] = {
    val (e, refAttr, refId) = (es.last, s":${ref.ns}/${ref.refAttr}", vv)
    firstEid = refId // for composites in nested
    nestedIds += e
    if (ref.bidirectional) {
      where += s"(rule$e $e $refId)" -> wClause
      rules += s"[(rule$e $e $refId) [$e $refAttr $refId]]"
      rules += s"[(rule$e $e $refId) [$refId $refAttr $e]]"
    } else {
      where += s"[$e $refAttr $refId]" -> wClause
    }
    // Start new level of casts
    castss = castss :+ Nil
    sortNestedLevel()
    es :+ refId
  }

  private def sortNestedLevel(): Unit = {
    val nestedIndex           = nestedIds.length - 1
    val levelIdSorter         = (_: Int) => (a: Row, b: Row) =>
      a.get(nestedIndex).asInstanceOf[jLong].compareTo(b.get(nestedIndex).asInstanceOf[jLong])
    val dummyIndexOfNestedEid = 6
    sortss = sortss.init :+ (sortss.last :+ (dummyIndexOfNestedEid, levelIdSorter))
    sortss = sortss :+ Nil
  }

  protected def resolveNestedOptRef(e: Var, nestedRef: Ref): Unit = {
    nestedOptIds += e
    if (where.isEmpty) {
      val Ref(ns, refAttrClean, _, _, _) = nestedRef
      val (refAttr, refId)               = (s":$ns/$refAttrClean", vv)
      where += s"[$e $refAttr $refId]" -> wClause
    }

    if (where.length == 1 && where.head._1.startsWith("[(identity")) {
      throw ModelError("Single optional attribute before optional nested data structure is not allowed.")
    }

    // Add nested caster
    castss = (castss.head :+ pullNestedData) +: castss.tail

    // Start new level of casts
    castss = castss :+ Nil
  }
}