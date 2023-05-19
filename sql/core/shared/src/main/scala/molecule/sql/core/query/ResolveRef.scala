package molecule.sql.core.query

import java.lang.{Long => jLong}
import molecule.base.ast.SchemaAST.CardOne
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.sql.core.query.casting.NestOpt_


trait ResolveRef[Tpl] { self: NestOpt_[Tpl] with Base =>

  protected def resolveRef(ref: Ref, curTable: String): Unit = {
    val (ns, refAttr, refNs) = (ref.ns, ref.refAttr, ref.refNs)

//    println(s"$ns  $refAttr  $refNs")

    val curTable1 = if(curTable.nonEmpty) curTable else refNs
    if (ref.card == CardOne) {
      joins += (("INNER JOIN", refNs, curTable, s"$ns.$refAttr", s"$curTable1.id"))
    } else {
      val joinTable  = ns + "_" + refAttr + "_" + refNs
      val (id1, id2) = if (ns == refNs) ("1_id", "2_id") else ("id", "id")
      joins += (("INNER JOIN", joinTable, "", s"$ns.id", s"$joinTable.${ns}_$id1"))
      joins += (("INNER JOIN", refNs, curTable, s"$joinTable.${refNs}_$id2", s"$curTable1.id"))
    }
  }

  protected def resolveNestedRef(ref: Ref): Unit = {
    val (e, refAttr, refId) = ("x", s":${ref.ns}/${ref.refAttr}", vv)
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
  }

  private def sortNestedLevel(): Unit = {
    val nestedIndex           = nestedIds.length - 1
    val levelIdSorter         = (_: Int) => (a: RowOLD, b: RowOLD) =>
      a.get(nestedIndex).asInstanceOf[jLong].compareTo(b.get(nestedIndex).asInstanceOf[jLong])
    val dummyIndexOfNestedEid = 6
    sortss = sortss.init :+ (sortss.last :+ (dummyIndexOfNestedEid, levelIdSorter))
    sortss = sortss :+ Nil
  }

  protected def resolveNestedOptRef(nestedRef: Ref): Unit = {
    val e = "xx"
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