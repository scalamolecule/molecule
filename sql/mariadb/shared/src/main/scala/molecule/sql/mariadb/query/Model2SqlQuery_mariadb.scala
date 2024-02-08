package molecule.sql.mariadb.query

import molecule.boilerplate.ast.Model._
import molecule.sql.core.query._


class Model2SqlQuery_mariadb[Tpl](elements0: List[Element])
  extends Model2SqlQuery[Tpl](elements0)
    with ResolveExprOne_mariadb
    with ResolveExprSet_mariadb
    with ResolveExprSetRefAttr_mariadb
    with SqlQueryBase {


  override def pagination(optLimit: Option[Int], optOffset: Option[Int], isBackwards: Boolean): String = {
    if (isNestedMan || isNestedOpt) {
      ""
    } else if (hardLimit == 0) {
      if (!isBackwards) {
        (optOffset, optLimit) match {
          case (None, None)                => ""
          case (None, Some(limit))         => s"\nLIMIT 0, $limit"
          case (Some(offset), None)        => s"\nLIMIT $offset, 18446744073709551615"
          case (Some(offset), Some(limit)) =>
            s"\nLIMIT $offset, $limit"
        }
      } else {
        (optOffset, optLimit) match {
          case (None, None)                => ""
          case (None, Some(limit))         => s"\nLIMIT 0, ${-limit}"
          case (Some(offset), None)        => s"\nLIMIT ${-offset}, 18446744073709551615"
          case (Some(offset), Some(limit)) => s"\nLIMIT ${-offset}, ${-limit}"
        }
      }
    } else {
      optOffset match {
        case None                        => s"\nLIMIT 0, $hardLimit"
        case Some(offset) if isBackwards => s"\nLIMIT ${-offset}, $hardLimit"
        case Some(offset)                => s"\nLIMIT $offset, $hardLimit"
      }
    }
  }

  override protected def resolveNestedRef(ref: Ref): Unit = {
    val Ref(ns, refAttr, refNs, _, _, _) = ref
    val (as, ext)                        = exts(refNs).fold(("", ""))(ext => (refNs + ext, ext))
    val nsExt                            = exts(ns).getOrElse("")

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
    val (as, ext)                        = exts(refNs).fold(("", ""))(ext => (refNs + ext, ext))
    val nsExt                            = exts(ns).getOrElse("")

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