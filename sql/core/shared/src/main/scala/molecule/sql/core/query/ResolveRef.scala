package molecule.sql.core.query

import java.lang.{Long => jLong}
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.sql.core.query.casting.NestOpt_


trait ResolveRef[Tpl] { self: NestOpt_[Tpl] with Base =>

  protected def resolveRef(es: List[Var], ref: Ref): List[Var] = {
    val (ns, refAttr, refNs) = (ref.ns, ref.refAttr, ref.refNs)
    joins.+=(("inner", refNs, s"$ns.$refAttr"))

    //    val (e, refAttr, refId) = (es.last, s":${ref.ns}/${ref.refAttr}", vv)
    //    if (ref.bidirectional) {
    //      whereOLD += s"(rule$e $e $refId)" -> wClause
    //      rules += s"[(rule$e $e $refId) [$e $refAttr $refId]]"
    //      rules += s"[(rule$e $e $refId) [$refId $refAttr $e]]"
    //    } else {
    //      whereOLD += s"[$e $refAttr $refId]" -> wClause
    //    }
    //    es :+ refId
    Nil
  }

  protected def resolveNestedRef(es: List[Var], ref: Ref): List[Var] = {
    val (e, refAttr, refId) = (es.last, s":${ref.ns}/${ref.refAttr}", vv)
    firstEid = refId // for composites in nested
    nestedIds += e
    if (ref.bidirectional) {
      whereOLD += s"(rule$e $e $refId)" -> wClause
      rules += s"[(rule$e $e $refId) [$e $refAttr $refId]]"
      rules += s"[(rule$e $e $refId) [$refId $refAttr $e]]"
    } else {
      whereOLD += s"[$e $refAttr $refId]" -> wClause
    }
    // Start new level of casts
    castssOLD = castssOLD :+ Nil
    sortNestedLevel()
    es :+ refId
  }

  private def sortNestedLevel(): Unit = {
    val nestedIndex           = nestedIds.length - 1
    val levelIdSorter         = (_: Int) => (a: RowOLD, b: RowOLD) =>
      a.get(nestedIndex).asInstanceOf[jLong].compareTo(b.get(nestedIndex).asInstanceOf[jLong])
    val dummyIndexOfNestedEid = 6
    sortss = sortss.init :+ (sortss.last :+ (dummyIndexOfNestedEid, levelIdSorter))
    sortss = sortss :+ Nil
  }

  protected def resolveNestedOptRef(e: Var, nestedRef: Ref): Unit = {
    nestedOptIds += e
    if (whereOLD.isEmpty) {
      val Ref(ns, refAttrClean, _, _, _) = nestedRef
      val (refAttr, refId)               = (s":$ns/$refAttrClean", vv)
      whereOLD += s"[$e $refAttr $refId]" -> wClause
    }

    if (whereOLD.length == 1 && whereOLD.head._1.startsWith("[(identity")) {
      throw ModelError("Single optional attribute before optional nested data structure is not allowed.")
    }

    // Add nested caster
    castssOLD = (castssOLD.head :+ pullNestedData) +: castssOLD.tail

    // Start new level of casts
    castssOLD = castssOLD :+ Nil
  }
}