package molecule.sql.postgres.transaction

import java.sql.Statement
import molecule.core.transaction.{InsertResolvers_, ResolveInsert}
import molecule.sql.core.transaction.{JoinTable, Table}
import molecule.sql.h2.transaction.Insert_h2

trait Insert_postgres extends Insert_h2 { self: ResolveInsert with InsertResolvers_ =>

  doPrint = false

  override protected def initInserts(): Unit = {
    inserts.foreach {
      case (refPath, cols) =>
        val table             = refPath.last
        val columns           = cols.map(_._1).mkString(",\n  ")
        val inputPlaceholders = cols.map { case (_, castExt) => s"?$castExt" }.mkString(", ")
        val stmt              =
          s"""INSERT INTO $table (
             |  $columns
             |) VALUES ($inputPlaceholders)""".stripMargin

        debug(s"B -------------------- refPath: $refPath")
        debug(stmt)
        val ps = sqlConn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS)
        tableDatas(refPath) = Table(refPath, stmt, ps)
        rowSettersMap(refPath) = Nil
    }

    joins.foreach {
      case (joinRefPath, id1, id2, leftPath, rightPath) =>
        val joinTable = joinRefPath.last
        val stmt      = s"INSERT INTO $joinTable ($id1, $id2) VALUES (?, ?)"
        val ps        = sqlConn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS)
        joinTableDatas = joinTableDatas :+ JoinTable(stmt, ps, leftPath, rightPath)
    }
  }

  override protected lazy val extsString     = List("", "VARCHAR")
  override protected lazy val extsInt        = List("", "INTEGER")
  override protected lazy val extsLong       = List("", "BIGINT")
  override protected lazy val extsFloat      = List("", "DECIMAL")
  override protected lazy val extsDouble     = List("", "DOUBLE")
  override protected lazy val extsBoolean    = List("", "BOOLEAN")
  override protected lazy val extsBigInt     = List("", "DECIMAL")
  override protected lazy val extsBigDecimal = List("", "DECIMAL")
  override protected lazy val extsDate       = List("", "DATE")
  override protected lazy val extsUUID       = List("::uuid", "UUID")
  override protected lazy val extsURI        = List("", "VARCHAR")
  override protected lazy val extsByte       = List("", "SMALLINT")
  override protected lazy val extsShort      = List("", "SMALLINT")
  override protected lazy val extsChar       = List("", "CHAR")
}