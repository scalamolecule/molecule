package molecule.sql.postgres.transaction

import java.sql.{PreparedStatement => PS}
import molecule.core.transaction.ResolveSave
import molecule.sql.core.transaction.{SqlSave, Table}

trait Save_postgres extends SqlSave with TxBase_postgres { self: ResolveSave =>

  doPrint = false

  override protected def insertEmptyRowStmt(
    table: String, cols: List[(String, String)]
  ): String = {
    val columns           = cols.map(_._1).mkString(",\n  ")
    val inputPlaceholders = cols.map {
      case (_, castExt) => s"?$castExt"
    }.mkString(", ")
    if (cols.nonEmpty) {
      s"""INSERT INTO $table (
         |  $columns
         |) VALUES ($inputPlaceholders)""".stripMargin
    } else {
      s"INSERT INTO $table (id) VALUES (DEFAULT)"
    }
  }


  override protected def addMap[T](
    ns: String,
    attr: String,
    optMap: Option[Map[String, T]],
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    val paramIndex1 = save.paramIndex
    optMap match {
      case Some(map: Map[_, _]) if map.nonEmpty =>
        save.add(attr, (ps: PS) =>
          ps.setString(paramIndex1, map2json(map, value2json)), "?", "::jsonb")
      case _                                    =>
        save.add(attr, (ps: PS) => ps.setNull(paramIndex1, 0))
    }


    val (curPath, paramIndex) = getParamIndex(attr, castExt = "::jsonb")
    val colSetter: Setter     = optMap match {
      case Some(map: Map[_, _]) if map.nonEmpty =>
        (ps: PS, _: IdsMap, _: RowIndex) =>
          ps.setString(paramIndex, map2json(map, value2json))

      case _ =>
        (ps: PS, _: IdsMap, _: RowIndex) =>
          ps.setNull(paramIndex, 0)
    }
    addColSetter(curPath, colSetter)
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
  override protected lazy val extsUUID           = List("UUID", "UUID", "::uuid") // UUIDs require casting in postgres
  override protected lazy val extsURI            = List("URI", "VARCHAR", "")
  override protected lazy val extsByte           = List("Byte", "SMALLINT", "")
  override protected lazy val extsShort          = List("Short", "SMALLINT", "")
  override protected lazy val extsChar           = List("Char", "TEXT", "")
}