package molecule.db.sql.postgres.transaction

import java.sql.PreparedStatement as PS
import molecule.db.core.transaction.{InsertResolvers, ResolveInsert}
import molecule.db.sql.core.transaction.SqlInsert
import molecule.db.sql.core.transaction.strategy.SqlOps

trait Insert_postgres
  extends SqlInsert { self: ResolveInsert & InsertResolvers & SqlOps =>

  override protected def addMap[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    val paramIndex   = insertAction.setCol(attr, "::jsonb")
    val stableInsert = insertAction
    (tpl: Product) => {
      tpl.productElement(tplIndex).asInstanceOf[Map[String, ?]] match {
        case map if map.nonEmpty =>
          stableInsert.addColSetter((ps: PS) =>
            ps.setString(
              paramIndex,
              map2json(map.asInstanceOf[Map[String, T]], value2json)
            ))

        case _ =>
          stableInsert.addColSetter((ps: PS) =>
            ps.setNull(paramIndex, java.sql.Types.NULL))
      }
    }
  }

  override protected def addMapOpt[T](
    ent: String,
    attr: String,
    optRef: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    val paramIndex   = insertAction.setCol(attr, "::jsonb")
    val stableInsert = insertAction
    (tpl: Product) => {
      tpl.productElement(tplIndex) match {
        case Some(map: Map[_, _]) if map.nonEmpty =>
          stableInsert.addColSetter((ps: PS) =>
            ps.setString(
              paramIndex,
              map2json(map.asInstanceOf[Map[String, T]], value2json)
            ))

        case _ =>
          stableInsert.addColSetter((ps: PS) =>
            ps.setNull(paramIndex, java.sql.Types.NULL))
      }
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