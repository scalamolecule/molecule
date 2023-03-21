package molecule.datomic.query

import java.lang.{Long => jLong}
import molecule.base.util.exceptions.ExecutionError
import molecule.boilerplate.ast.Model._
import molecule.datomic.query.casting.NestOpt_


trait ResolveRef[Tpl] { self: Base[Tpl] with NestOpt_[Tpl] =>

  protected def resolveRef(es: List[Var], ref: Ref): List[Var] = {
    val (e, refAttr, refId) = (es.last, s":${ref.ns}/${ref.refAttr}", vv)
    where += s"[$e $refAttr $refId]" -> wClause
    es :+ refId
  }

  protected def resolveNestedRef(es: List[Var], ref: Ref): List[Var] = {
    val (e, refAttr, refId) = (es.last, s":${ref.ns}/${ref.refAttr}", vv)
    firstEid = refId // for composites in nested
    nestedIds += e
    where += s"[$e $refAttr $refId]" -> wClause

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
      val Ref(ns, refAttr, _, _) = nestedRef
      val (refA, refId)          = (s":$ns/$refAttr", vv)
      where += s"[$e $refA $refId]" -> wClause
    }
    if (where.length == 1 && where.head._1.startsWith("[(identity")) {
      throw ExecutionError("Single optional attribute before optional nested data structure is not allowed.")
    }

    // Add nested caster
    castss = (castss.head :+ pullNestedData) +: castss.tail

    // Start new level of casts
    castss = castss :+ Nil
  }
}