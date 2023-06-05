package molecule.sql.core.query

import java.lang.{Long => jLong}
import molecule.base.ast.SchemaAST.CardOne
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.sql.core.query.casting.NestOpt_


trait ResolveRef[Tpl] { self: NestOpt_[Tpl] with Base =>

  protected def resolveRef(ref: Ref): Unit = {
    val (ns, refAttr, refNs) = (ref.ns, ref.refAttr, ref.refNs)
    val (as, ext)            = exts(refNs).fold(("", ""))(ext => (refNs + ext, ext))
    //    println(s"====  $ns  $refAttr  $refNs  $as  $ext")
    if (ref.card == CardOne) {
      joins += (("INNER JOIN", refNs, as, s"$ns.$refAttr", s"$refNs$ext.id"))
    } else {
      val joinTable  = ns + "_" + refAttr + "_" + refNs
      val (id1, id2) = if (ns == refNs) ("1_id", "2_id") else ("id", "id")
      joins += (("INNER JOIN", joinTable, "", s"$ns.id", s"$joinTable.${ns}_$id1"))
      joins += (("INNER JOIN", refNs, as, s"$joinTable.${refNs}_$id2", s"$refNs$ext.id"))
    }
  }

  protected def resolveNestedRef(ref: Ref): Unit = {
    val Ref(ns, refAttr, refNs, _, _) = ref
    val (as, ext)                     = exts(refNs).fold(("", ""))(ext => (refNs + ext, ext))
    nestedIds += s"$ns.id"
    val joinTable  = ns + "_" + refAttr + "_" + refNs
    val (id1, id2) = if (ns == refNs) ("1_id", "2_id") else ("id", "id")
    joins += (("INNER JOIN", joinTable, "", s"$ns.id", s"$joinTable.${ns}_$id1"))
    joins += (("INNER JOIN", refNs, as, s"$joinTable.${refNs}_$id2", s"$refNs$ext.id"))
    castss = castss :+ Nil
  }

  protected def resolveNestedOptRef(nestedRef: Ref): Unit = {
//    val e = "xx"
//    nestedOptIds += e
//    if (whereOLD.isEmpty) {
//      val Ref(ns, refAttrClean, _, _, _) = nestedRef
//      val (refAttr, refId)               = (s":$ns/$refAttrClean", vv)
//      whereOLD += s"[$e $refAttr $refId]" -> wClause
//    }
//
//    if (whereOLD.length == 1 && whereOLD.head._1.startsWith("[(identity")) {
//      throw ModelError("Single optional attribute before optional nested data structure is not allowed.")
//    }
//
//    // Add nested caster
//    castssOLD = (castssOLD.head :+ pullNestedData) +: castssOLD.tail
//
//    // Start new level of casts
//    castssOLD = castssOLD :+ Nil


    val Ref(ns, refAttr, refNs, _, _) = nestedRef
    val (as, ext)                     = exts(refNs).fold(("", ""))(ext => (refNs + ext, ext))
    nestedIds += s"$ns.id"
    val joinTable  = ns + "_" + refAttr + "_" + refNs
    val (id1, id2) = if (ns == refNs) ("1_id", "2_id") else ("id", "id")
    joins += (("LEFT JOIN", joinTable, "", s"$ns.id", s"$joinTable.${ns}_$id1"))
    joins += (("LEFT JOIN", refNs, as, s"$joinTable.${refNs}_$id2", s"$refNs$ext.id"))
    castss = castss :+ Nil
  }
}