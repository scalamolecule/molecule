package molecule.sql.postgres.transaction

import java.sql.{Statement, PreparedStatement => PS}
import molecule.core.transaction.ResolveSave
import molecule.sql.core.transaction.{SqlSave, Table}

trait Save_postgres extends SqlSave { self: ResolveSave =>

  doPrint = false

  override protected def addRowSetterToTables(): Unit = {
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

        val colSetters = colSettersMap(refPath)
        debug(s"--- save -------------------  ${colSetters.length}  $refPath")
        debug(stmt)

        val ps = sqlConn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS)
        tableDatas(refPath) = Table(refPath, stmt, ps)


        colSettersMap(refPath) = Nil
        val rowSetter = (ps: PS, idsMap: IdsMap, _: RowIndex) => {
          // Set all column values for this row in this insert/batch
          colSetters.foreach(colSetter =>
            colSetter(ps, idsMap, 0)
          )
          // Complete row
          ps.addBatch()
        }
        rowSettersMap(refPath) = List(rowSetter)
    }
  }

  override protected lazy val extsString         = List("", "VARCHAR")
  override protected lazy val extsInt            = List("", "INTEGER")
  override protected lazy val extsLong           = List("", "BIGINT")
  override protected lazy val extsFloat          = List("", "DECIMAL")
  override protected lazy val extsDouble         = List("", "DOUBLE")
  override protected lazy val extsBoolean        = List("", "BOOLEAN")
  override protected lazy val extsBigInt         = List("", "DECIMAL")
  override protected lazy val extsBigDecimal     = List("", "DECIMAL")
  override protected lazy val extsDate           = List("", "DATE")
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
  override protected lazy val extsChar           = List("", "CHAR")
}