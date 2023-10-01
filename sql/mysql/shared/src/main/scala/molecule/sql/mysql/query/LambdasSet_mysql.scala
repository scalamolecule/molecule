package molecule.sql.mysql.query

import molecule.sql.core.query.{LambdasSet, SqlQueryBase}

trait LambdasSet_mysql extends LambdasSet { self: SqlQueryBase =>

  override protected lazy val tpeDbString    : String = "LONGTEXT"
  override protected lazy val tpeDbInt       : String = "INT"
  override protected lazy val tpeDbLong      : String = "BIGINT"
  override protected lazy val tpeDbFloat     : String = "REAL"
  override protected lazy val tpeDbDouble    : String = "DOUBLE"
  override protected lazy val tpeDbBoolean   : String = "TINYINT(1)"
  override protected lazy val tpeDbBigInt    : String = "DECIMAL(65, 0)"
  override protected lazy val tpeDbBigDecimal: String = "DECIMAL(65, 30)"
  override protected lazy val tpeDbDate      : String = "DATETIME"
  override protected lazy val tpeDbUUID      : String = "TINYTEXT"
  override protected lazy val tpeDbURI       : String = "TEXT"
  override protected lazy val tpeDbByte      : String = "TINYINT"
  override protected lazy val tpeDbShort     : String = "SMALLINT"
  override protected lazy val tpeDbChar      : String = "CHAR"
}
