package molecule.sql.postgres.query

import molecule.boilerplate.ast.Model._
import molecule.sql.core.query._


class Model2SqlQuery_postgres[Tpl](elements0: List[Element])
  extends Model2SqlQuery[Tpl](elements0)
    with ResolveExprOne_postgres
    with ResolveExprSet_postgres
    with ResolveExprSetRefAttr_postgres
    with SqlQueryBase {


  override protected def resolveNestedRef(ref: Ref): Unit = {
    val Ref(ns, refAttr, refNs, _, _) = ref
    val (as, ext)                     = exts(refNs).fold(("", ""))(ext => (refNs + ext, ext))
    val nsExt                         = exts(ns).getOrElse("")

    nestedIds += s"$ns.id"
    groupBy += s"$ns.id"
    aggregate = true

    val joinTable  = ss(ns, refAttr, refNs)
    val (id1, id2) = if (ns == refNs) ("1_id", "2_id") else ("id", "id")
    joins += (("INNER JOIN", joinTable, "", s"$ns$nsExt.id = $joinTable.${ns}_$id1"))
    joins += (("INNER JOIN", refNs, as, s"$joinTable.${refNs}_$id2 = $refNs$ext.id"))
    castss = castss :+ Nil
  }

  override protected def resolveNestedOptRef(nestedRef: Ref): Unit = {
    val Ref(ns, refAttr, refNs, _, _) = nestedRef
    val (as, ext)                     = exts(refNs).fold(("", ""))(ext => (refNs + ext, ext))
    val nsExt                         = exts(ns).getOrElse("")

    nestedIds += s"$ns.id"
    groupBy += s"$ns.id"
    aggregate = true

    val joinTable  = ss(ns, refAttr, refNs)
    val (id1, id2) = if (ns == refNs) ("1_id", "2_id") else ("id", "id")
    joins += (("LEFT JOIN", joinTable, "", s"$ns$nsExt.id = $joinTable.${ns}_$id1"))
    joins += (("LEFT JOIN", refNs, as, s"$joinTable.${refNs}_$id2 = $refNs$ext.id"))
    castss = castss :+ Nil
  }
}