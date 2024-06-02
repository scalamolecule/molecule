package molecule.sql.postgres.transaction

import java.sql.Statement
import molecule.core.transaction.{InsertResolvers_, ResolveInsert}
import molecule.sql.core.transaction.{JoinTable, SqlInsert, Table}
import java.sql.{PreparedStatement => PS}

trait Insert_postgres extends SqlInsert { self: ResolveInsert with InsertResolvers_ =>

  doPrint = false

  override protected def initInserts(): Unit = {
    inserts.foreach {
      case (refPath, cols) =>
        val table             = refPath.last
        val columns           = cols.map(_._1).mkString(",\n  ")
        val inputPlaceholders = cols.map { case (_, castExt) => s"?$castExt" }.mkString(", ")
        val stmt              = if (cols.nonEmpty) {
          s"""INSERT INTO $table (
             |  $columns
             |) VALUES ($inputPlaceholders)""".stripMargin
        } else {
          s"INSERT INTO $table DEFAULT VALUES"
        }

        debug(s"B -------------------- refPath: $refPath")
        debug(stmt)
        tableDatas(refPath) = Table(refPath, stmt)
        rowSettersMap(refPath) = Nil
    }

    joins.foreach {
      case (joinRefPath, id1, id2, leftPath, rightPath) =>
        val joinTable = joinRefPath.last
        val stmt      = s"INSERT INTO $joinTable ($id1, $id2) VALUES (?, ?)"
        joinTableDatas = joinTableDatas :+ JoinTable(stmt, preparedStmt(stmt), leftPath, rightPath)
    }
  }

  override protected def addMap[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    val (curPath, paramIndex) = getParamIndex(attr, castExt = "::jsonb")
    (tpl: Product) =>
      val colSetter = tpl.productElement(tplIndex).asInstanceOf[Map[String, _]] match {
        case map if map.nonEmpty =>
          (ps: PS, _: IdsMap, _: RowIndex) =>
            ps.setString(paramIndex, map2json(map.asInstanceOf[Map[String, T]], value2json))

        case _ =>
          (ps: PS, _: IdsMap, _: RowIndex) =>
            ps.setNull(paramIndex, java.sql.Types.NULL)
      }
      addColSetter(curPath, colSetter)
  }

  override protected def addMapOpt[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    val (curPath, paramIndex) = getParamIndex(attr, castExt = "::jsonb")
    (tpl: Product) =>
      val colSetter = tpl.productElement(tplIndex) match {
        case Some(map: Map[_, _]) if map.nonEmpty =>
          (ps: PS, _: IdsMap, _: RowIndex) =>
            ps.setString(paramIndex, map2json(map.asInstanceOf[Map[String, T]], value2json))

        case _ =>
          (ps: PS, _: IdsMap, _: RowIndex) =>
            ps.setNull(paramIndex, java.sql.Types.NULL)
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
  override protected lazy val extsUUID           = List("UUID", "UUID", "::uuid")
  override protected lazy val extsURI            = List("URI", "VARCHAR", "")
  override protected lazy val extsByte           = List("Byte", "SMALLINT", "")
  override protected lazy val extsShort          = List("Short", "SMALLINT", "")
  override protected lazy val extsChar           = List("Char", "TEXT", "")
}