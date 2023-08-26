package molecule.sql.core.query

import molecule.base.ast.SchemaAST.CardOne
import molecule.boilerplate.ast.Model._


trait ResolveRef { self: SqlQueryBase =>

  protected def resolveRef(ref: Ref): Unit = {
    val (ns, refAttr, refNs) = (ref.ns, ref.refAttr, ref.refNs)
    val (as, ext)            = exts(refNs).fold(("", ""))(ext => (refNs + ext, ext))
    if (ref.card == CardOne) {
      joins += (("INNER JOIN", refNs, as, s"$ns.$refAttr = $refNs$ext.id"))
    } else {
      val joinTable  = ns + "_" + refAttr + "_" + refNs
      val (id1, id2) = if (ns == refNs) ("1_id", "2_id") else ("id", "id")
      joins += (("INNER JOIN", joinTable, "", s"$ns.id = $joinTable.${ns}_$id1"))
      joins += (("INNER JOIN", refNs, as, s"$joinTable.${refNs}_$id2 = $refNs$ext.id"))
    }
  }

  protected def resolveCompositeJoin(nsA: String, nsB: String, compositeGroup: Int): Unit = {
    val cj = "cj" + compositeGroup
    joins += (("INNER JOIN", "CompositeJoin", cj, s"$cj.a = '$nsA' AND $cj.a_id = $nsA.id"))
    joins += (("INNER JOIN", nsB, "",             s"$cj.b = '$nsB' AND $cj.b_id = $nsB.id"))
  }

  protected def resolveNestedRef(ref: Ref): Unit = {
    val Ref(ns, refAttr, refNs, _, _) = ref
    val (as, ext)                     = exts(refNs).fold(("", ""))(ext => (refNs + ext, ext))
    nestedIds += s"$ns.id"
    val joinTable  = ns + "_" + refAttr + "_" + refNs
    val (id1, id2) = if (ns == refNs) ("1_id", "2_id") else ("id", "id")
    joins += (("INNER JOIN", joinTable, "", s"$ns.id = $joinTable.${ns}_$id1"))
    joins += (("INNER JOIN", refNs, as, s"$joinTable.${refNs}_$id2 = $refNs$ext.id"))
    castss = castss :+ Nil
  }

  protected def resolveNestedOptRef(nestedRef: Ref): Unit = {
    val Ref(ns, refAttr, refNs, _, _) = nestedRef
    val (as, ext)                     = exts(refNs).fold(("", ""))(ext => (refNs + ext, ext))
    nestedIds += s"$ns.id"
    val joinTable  = ns + "_" + refAttr + "_" + refNs
    val (id1, id2) = if (ns == refNs) ("1_id", "2_id") else ("id", "id")
    joins += (("LEFT JOIN", joinTable, "", s"$ns.id = $joinTable.${ns}_$id1"))
    joins += (("LEFT JOIN", refNs, as, s"$joinTable.${refNs}_$id2 = $refNs$ext.id"))
    castss = castss :+ Nil
  }
}