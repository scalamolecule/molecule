package molecule.document.mongodb.query

import molecule.base.ast._
import molecule.boilerplate.ast.Model._


trait ResolveRef { self: MongoQueryBase =>

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
      val joinTable  = ss(ns, refAttr, refNs)
      val (id1, id2) = if (ns == refNs) ("1_id", "2_id") else ("id", "id")
      val ns_id1     = ss(ns, id1)
      val refNs_id2  = ss(refNs, id2)
      joins += (("INNER JOIN", joinTable, "", s"$ns.id = $joinTable.$ns_id1"))
      joins += (("INNER JOIN", refNs, refAs, s"$joinTable.$refNs_id2 = $refNs$refExt.id"))
    }
  }

  protected def resolveNestedRef(ref: Ref): Unit = {
    val Ref(ns, refAttr, refNs, _, _) = ref
    val (as, ext)                     = exts(refNs).fold(("", ""))(ext => (refNs + ext, ext))
    val nsExt                         = exts(ns).getOrElse("")

    nestedIds += s"$ns.id"
    val joinTable  = ss(ns, refAttr, refNs)
    val (id1, id2) = if (ns == refNs) ("1_id", "2_id") else ("id", "id")
    val ns_id1     = ss(ns, id1)
    val refNs_id2  = ss(refNs, id2)
    joins += (("INNER JOIN", joinTable, "", s"$ns$nsExt.id = $joinTable.$ns_id1"))
    joins += (("INNER JOIN", refNs, as, s"$joinTable.$refNs_id2 = $refNs$ext.id"))
    castss = castss :+ Nil
  }

  protected def resolveNestedOptRef(nestedRef: Ref): Unit = {
    val Ref(ns, refAttr, refNs, _, _) = nestedRef
    val (as, ext)                     = exts(refNs).fold(("", ""))(ext => (refNs + ext, ext))
    val nsExt                         = exts(ns).getOrElse("")

    nestedIds += s"$ns.id"
    val joinTable  = ss(ns, refAttr, refNs)
    val (id1, id2) = if (ns == refNs) ("1_id", "2_id") else ("id", "id")
    val ns_id1     = ss(ns, id1)
    val refNs_id2  = ss(refNs, id2)
    joins += (("LEFT JOIN", joinTable, "", s"$ns$nsExt.id = $joinTable.$ns_id1"))
    joins += (("LEFT JOIN", refNs, as, s"$joinTable.$refNs_id2 = $refNs$ext.id"))
    castss = castss :+ Nil
  }
}
