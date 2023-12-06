package molecule.datalog.core.query

import java.lang.{Long => jLong}
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.datalog.core.query.casting.NestOpt_


trait ResolveRef[Tpl] { self: DatomicQueryBase with NestOpt_[Tpl] =>

  protected def resolveRef(es: List[Var], ref: Ref): List[Var] = {
    val (e, refAttr, refId) = (es.last, s":${ref.ns}/${ref.refAttr}", vv)
    where += s"[$e $refAttr $refId]" -> wClause
    es :+ refId
  }

  protected def resolveNestedRef(es: List[Var], ref: Ref): List[Var] = {
    val (e, refAttr, refId) = (es.last, s":${ref.ns}/${ref.refAttr}", vv)
    firstId = refId
    val nestedId = "?id" + nestedIds.size
    nestedIds += nestedId
    where += s"[(identity $e) $nestedId]" -> wGround
    where += s"[$e $refAttr $refId]" -> wClause
    // Start new level of casts
    castss = castss :+ Nil
    sortNestedLevel()
    es :+ refId
  }

  private def sortNestedLevel(): Unit = {
    val nestedIndex          = nestedIds.length - 1
    val levelIdSorter        = (_: Int) => (a: Row, b: Row) =>
      a.get(nestedIndex).asInstanceOf[jLong].compareTo(b.get(nestedIndex).asInstanceOf[jLong])
    val dummyIndexOfNestedId = 6
    sortss = sortss.init :+ (sortss.last :+ (dummyIndexOfNestedId, levelIdSorter))
    sortss = sortss :+ Nil
  }

  protected def resolveNestedOptRef(e: Var, nestedRef: Ref): Unit = {
    val nestedId = "?id" + nestedIds.size
    if (where.isEmpty) {
      val Ref(ns, refAttrClean, _, _, _, _) = nestedRef
      val (refAttr, refId)                  = (s":$ns/$refAttrClean", vv)
      where += s"[$e $refAttr $refId]" -> wClause
    }
    where += s"[(identity $e) $nestedId]" -> wGround

    if (where.length == 2 && where.head._1.startsWith("[(identity")) {
      /*
      When only one identity function for single optional attribute and one for identity of pull entity id

      ([(identity ?a) ?a-?b],1)
      ([(identity ?a) ?id0],1)
       */
      throw ModelError("Single optional attribute before optional nested data structure is not allowed.")
    }

    // Add nested caster
    castss = (castss.head :+ pullNestedData) +: castss.tail

    // Start new level of casts
    castss = castss :+ Nil
  }
}