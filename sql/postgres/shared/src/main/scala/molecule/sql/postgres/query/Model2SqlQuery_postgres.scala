package molecule.sql.postgres.query

import molecule.boilerplate.ast.Model._
import molecule.sql.core.query._


class Model2SqlQuery_postgres[Tpl](elements0: List[Element])
  extends Model2SqlQuery[Tpl](elements0)
    with ResolveExprOne_postgres
    with ResolveExprSet_postgres
    with ResolveExprSeq_postgres
    with ResolveExprMap_postgres
    with ResolveExprSetRefAttr_postgres
    with SqlQueryBase {


  override protected def resolveNestedRef(ref: Ref): Unit = {
    val Ref(ns, refAttr, refNs, _, _, _) = ref

    val (as, ext) = getOptExt().fold(("", ""))(ext => (refNs + ext, ext))
    val nsExt     = getOptExt(path.dropRight(2)).getOrElse("")

    nestedIds += s"$ns.id"
    groupBy += s"$ns.id"
    aggregate = true

    val joinTable  = ss(ns, refAttr, refNs)
    val (id1, id2) = if (ns == refNs) ("1_id", "2_id") else ("id", "id")
    joins += (("INNER JOIN", joinTable, "", s"$ns$nsExt.id", s"= $joinTable.${ns}_$id1"))
    joins += (("INNER JOIN", refNs, as, s"$joinTable.${refNs}_$id2", s"= $refNs$ext.id"))
    castss = castss :+ Nil
  }

  override protected def resolveNestedOptRef(nestedRef: Ref): Unit = {
    val Ref(ns, refAttr, refNs, _, _, _) = nestedRef

    val (as, ext) = getOptExt().fold(("", ""))(ext => (refNs + ext, ext))
    val nsExt     = getOptExt(path.dropRight(2)).getOrElse("")

    nestedIds += s"$ns.id"
    groupBy += s"$ns.id"
    aggregate = true

    val joinTable  = ss(ns, refAttr, refNs)
    val (id1, id2) = if (ns == refNs) ("1_id", "2_id") else ("id", "id")
    joins += (("LEFT JOIN", joinTable, "", s"$ns$nsExt.id", s"= $joinTable.${ns}_$id1"))
    joins += (("LEFT JOIN", refNs, as, s"$joinTable.${refNs}_$id2", s"= $refNs$ext.id"))
    castss = castss :+ Nil
  }
}