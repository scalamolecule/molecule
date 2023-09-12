package molecule.sql.core.query

import molecule.base.ast._
import molecule.boilerplate.ast.Model._


trait ResolveRef { self: SqlQueryBase =>

  protected def resolveRef(ref: Ref): Unit = {
    val (ns, refAttr, refNs) = (ref.ns, ref.refAttr, ref.refNs)
    val (refAs, refExt)      = exts(refNs).fold(("", ""))(ext => (refNs + ext, ext))
    val nsExt                = if (ns == refNs)
      "" // self-joins
    else
      exts(ns).getOrElse("")

    if (ref.card == CardOne) {
      val joinType = if (isNestedOpt) "LEFT" else "INNER"
      joins += ((s"$joinType JOIN", refNs, refAs, s"$ns$nsExt.$refAttr = $refNs$refExt.id"))
    } else {
      val joinTable  = ns + "_" + refAttr + "_" + refNs
      val (id1, id2) = if (ns == refNs) ("1_id", "2_id") else ("id", "id")
      joins += (("INNER JOIN", joinTable, "", s"$ns.id = $joinTable.${ns}_$id1"))
      joins += (("INNER JOIN", refNs, refAs, s"$joinTable.${refNs}_$id2 = $refNs$refExt.id"))
    }
  }

  protected def resolveNestedRef(ref: Ref): Unit = {
    val Ref(ns, refAttr, refNs, _) = ref
    val (as, ext)                  = exts(refNs).fold(("", ""))(ext => (refNs + ext, ext))
    val nsExt                      = exts(ns).getOrElse("")

    nestedIds += s"$ns.id"
    val joinTable  = ns + "_" + refAttr + "_" + refNs
    val (id1, id2) = if (ns == refNs) ("1_id", "2_id") else ("id", "id")
    joins += (("INNER JOIN", joinTable, "", s"$ns$nsExt.id = $joinTable.${ns}_$id1"))
    joins += (("INNER JOIN", refNs, as, s"$joinTable.${refNs}_$id2 = $refNs$ext.id"))
    castss = castss :+ Nil
  }

  protected def resolveNestedOptRef(nestedRef: Ref): Unit = {
    val Ref(ns, refAttr, refNs, _) = nestedRef
    val (as, ext)                  = exts(refNs).fold(("", ""))(ext => (refNs + ext, ext))
    val nsExt                      = exts(ns).getOrElse("")

    nestedIds += s"$ns.id"
    val joinTable  = ns + "_" + refAttr + "_" + refNs
    val (id1, id2) = if (ns == refNs) ("1_id", "2_id") else ("id", "id")
    joins += (("LEFT JOIN", joinTable, "", s"$ns$nsExt.id = $joinTable.${ns}_$id1"))
    joins += (("LEFT JOIN", refNs, as, s"$joinTable.${refNs}_$id2 = $refNs$ext.id"))
    castss = castss :+ Nil
  }
}