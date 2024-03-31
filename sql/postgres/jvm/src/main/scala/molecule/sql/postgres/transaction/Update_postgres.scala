package molecule.sql.postgres.transaction

import java.sql.{Statement, PreparedStatement => PS}
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.transaction.ResolveUpdate
import molecule.sql.core.query.Model2SqlQuery
import molecule.sql.core.transaction.{SqlUpdate, Table}
import molecule.sql.postgres.query.Model2SqlQuery_postgres
import scala.annotation.tailrec

trait Update_postgres extends SqlUpdate { self: ResolveUpdate =>

  override def model2SqlQuery(elements: List[Element]): Model2SqlQuery[Any] =
    new Model2SqlQuery_postgres[Any](elements)


  override def updateSetRemove[T](
    ns: String,
    attr: String,
    refNs: Option[String],
    owner: Boolean,
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    one2json: T => String
  ): Unit = {
    refNs.fold {
      if (set.nonEmpty) {
        updateCurRefPath(attr)
        if (!isUpsert) {
          addToUpdateCols(ns, attr)
        }
        val cast = exts.head
        @tailrec
        def remove(swaps: Int, calls: String = "", args: String = ""): String = {
          if (swaps == 0)
            s"$calls$attr$args"
          else
            remove(swaps - 1, s"ARRAY_REMOVE($calls", s", ?$cast)$args")
        }
        placeHolders = placeHolders :+ (s"$attr = " + remove(set.size))
        addColSetter(curRefPath, (ps: PS, _: IdsMap, _: RowIndex) => {
          set.foreach { v =>
            transformValue(v).asInstanceOf[(PS, Int) => Unit](ps, curParamIndex)
            curParamIndex += 1
          }
        })
      }
    } { refNs =>
      if (set.nonEmpty) {
        // Separate update of ref ids in join table -----------------------------
        val refAttr    = attr
        val joinTable  = ss(ns, refAttr, refNs)
        val ns_id      = ss(ns, "id")
        val refNs_id   = ss(refNs, "id")
        val retractIds = set.mkString(s" AND $refNs_id IN (", ", ", ")")
        manualTableDatas = List(
          deleteJoins(joinTable, ns_id, getUpdateId, retractIds)
        )
      }
    }
  }

  override protected lazy val extsString         = List("", "VARCHAR")
  override protected lazy val extsInt            = List("", "INTEGER")
  override protected lazy val extsLong           = List("", "BIGINT")
  override protected lazy val extsFloat          = List("", "DECIMAL")
  override protected lazy val extsDouble         = List("", "DECIMAL")
  override protected lazy val extsBoolean        = List("", "BOOLEAN")
  override protected lazy val extsBigInt         = List("", "DECIMAL")
  override protected lazy val extsBigDecimal     = List("", "DECIMAL")
  override protected lazy val extsDate           = List("", "BIGINT")
  override protected lazy val extsDuration       = List("", "VARCHAR")
  override protected lazy val extsInstant        = List("", "VARCHAR")
  override protected lazy val extsLocalDate      = List("", "VARCHAR")
  override protected lazy val extsLocalTime      = List("", "VARCHAR")
  override protected lazy val extsLocalDateTime  = List("", "VARCHAR")
  override protected lazy val extsOffsetTime     = List("", "VARCHAR")
  override protected lazy val extsOffsetDateTime = List("", "VARCHAR")
  override protected lazy val extsZonedDateTime  = List("", "VARCHAR")
  override protected lazy val extsUUID           = List("::uuid", "UUID")
  override protected lazy val extsURI            = List("", "VARCHAR")
  override protected lazy val extsByte           = List("", "SMALLINT")
  override protected lazy val extsShort          = List("", "SMALLINT")
  override protected lazy val extsChar           = List("", "TEXT")
}