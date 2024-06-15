package molecule.sql.postgres.transaction

import java.sql.{PreparedStatement => PS}
import molecule.boilerplate.ast.Model._
import molecule.core.transaction.ResolveUpdate
import molecule.sql.core.query.Model2SqlQuery
import molecule.sql.core.transaction.SqlUpdate
import molecule.sql.postgres.query.Model2SqlQuery_postgres
import scala.annotation.tailrec

trait Update_postgres extends SqlUpdate { self: ResolveUpdate =>

  override def model2SqlQuery(elements: List[Element]): Model2SqlQuery[Any] =
    new Model2SqlQuery_postgres[Any](elements)


  override def updateSetRemove[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    owner: Boolean,
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    one2json: T => String,
    set2array: Set[T] => Array[AnyRef]
  ): Unit = {
    updateIterableRemove(ns, attr, optRefNs, set, transformValue, exts)
  }

  override def updateSeqRemove[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    owner: Boolean,
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    one2json: T => String,
    seq2array: Seq[T] => Array[AnyRef]
  ): Unit = {
    updateIterableRemove(ns, attr, optRefNs, seq, transformValue, exts)
  }

  override def updateMapEq[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    noValue: Boolean,
    owner: Boolean,
    map: Map[String, T],
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    updateMapEqJdbc(ns, attr, "::jsonb", map,
      (ps: PS, paramIndex: Int) =>
        ps.setString(paramIndex, map2json(map, value2json))
    )
  }

  override def updateMapAdd[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    owner: Boolean,
    map: Map[String, T],
    transformValue: T => Any,
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer,
    //    set2array: Set[Any] => Array[AnyRef],
  ): Unit = {
    updateMapAddJdbc[T](ns, attr, "::jsonb", map, exts,
      (ps: PS, paramIndex: Int, updatedMap: Map[String, T]) =>
        ps.setString(paramIndex, map2json(updatedMap, value2json))
    )
  }

  override def updateMapRemove[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    owner: Boolean,
    map: Map[String, T],
    transformValue: T => Any,
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer,
  ): Unit = {
    updateMapRemoveJdbc[T](ns, attr, "::jsonb", map, exts,
      (ps: PS, paramIndex: Int, updatedMap: Map[String, T]) =>
        ps.setString(paramIndex, map2json(updatedMap, value2json))
    )
  }

  // Helpers -------------------------------------------------------------------

  private def updateIterableRemove[T, M[_] <: Iterable[_]](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    iterable: Iterable[T],
    transformValue: T => Any,
    exts: List[String],
  ): Unit = {
    optRefNs.fold {
      if (iterable.nonEmpty) {
        cols += attr
        if (!isUpsert) {
          addToUpdateColsNotNull(ns, attr)
        }
        val cast = exts(2)
        @tailrec
        def remove(swaps: Int, calls: String = "", args: String = ""): String = {
          if (swaps == 0)
            s"$calls$attr$args"
          else
            remove(swaps - 1, s"ARRAY_REMOVE($calls", s", ?$cast)$args")
        }
        placeHolders = placeHolders :+ (s"$attr = " + remove(iterable.size))
        addColSetter(curRefPath, (ps: PS, _: IdsMap, _: RowIndex) => {
          iterable.foreach { v =>
            transformValue(v).asInstanceOf[(PS, Int) => Unit](ps, curParamIndex)
            curParamIndex += 1
          }
        })
      }
    } { refNs =>
      joinRemove(ns, attr, refNs, iterable)
    }
  }

  override protected lazy val extsID             = List("ID", "VARCHAR", "")
  override protected lazy val extsString         = List("String", "VARCHAR", "")
  override protected lazy val extsInt            = List("Int", "INTEGER", "")
  override protected lazy val extsLong           = List("Long", "BIGINT", "")
  override protected lazy val extsFloat          = List("Float", "DECIMAL", "")
  override protected lazy val extsDouble         = List("Double", "DECIMAL", "")
  override protected lazy val extsBoolean        = List("Boolean", "BOOLEAN", "")
  override protected lazy val extsBigInt         = List("BigInt", "DECIMAL", "")
  override protected lazy val extsBigDecimal     = List("BigDecimal", "DECIMAL", "")
  override protected lazy val extsDate           = List("Date", "BIGINT", "")
  override protected lazy val extsDuration       = List("Duration", "VARCHAR", "")
  override protected lazy val extsInstant        = List("Instant", "VARCHAR", "")
  override protected lazy val extsLocalDate      = List("LocalDate", "VARCHAR", "")
  override protected lazy val extsLocalTime      = List("LocalTime", "VARCHAR", "")
  override protected lazy val extsLocalDateTime  = List("LocalDateTime", "VARCHAR", "")
  override protected lazy val extsOffsetTime     = List("OffsetTime", "VARCHAR", "")
  override protected lazy val extsOffsetDateTime = List("OffsetDateTime", "VARCHAR", "")
  override protected lazy val extsZonedDateTime  = List("ZonedDateTime", "VARCHAR", "")
  override protected lazy val extsUUID           = List("UUID", "UUID", "::uuid")
  override protected lazy val extsURI            = List("URI", "VARCHAR", "")
  override protected lazy val extsByte           = List("Byte", "SMALLINT", "")
  override protected lazy val extsShort          = List("Short", "SMALLINT", "")
  override protected lazy val extsChar           = List("Char", "TEXT", "")
}