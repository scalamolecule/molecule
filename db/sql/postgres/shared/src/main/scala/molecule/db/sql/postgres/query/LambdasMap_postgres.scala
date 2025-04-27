package molecule.db.sql.postgres.query

import molecule.db.sql.core.query.{LambdasMap, SqlQueryBase}

trait LambdasMap_postgres extends LambdasMap { self: SqlQueryBase =>

  override protected lazy val tpeDbId            : String = "BIGINT"
  override protected lazy val tpeDbString        : String = "TEXT"
  override protected lazy val tpeDbInt           : String = "INTEGER"
  override protected lazy val tpeDbLong          : String = "BIGINT"
  override protected lazy val tpeDbFloat         : String = "DECIMAL"
  override protected lazy val tpeDbDouble        : String = "DOUBLE PRECISION"
  override protected lazy val tpeDbBoolean       : String = "BOOLEAN"
  override protected lazy val tpeDbBigInt        : String = "DECIMAL"
  override protected lazy val tpeDbBigDecimal    : String = "DECIMAL"
  override protected lazy val tpeDbDate          : String = "BIGINT"
  override protected lazy val tpeDbDuration      : String = "VARCHAR"
  override protected lazy val tpeDbInstant       : String = "VARCHAR"
  override protected lazy val tpeDbLocalDate     : String = "VARCHAR"
  override protected lazy val tpeDbLocalTime     : String = "VARCHAR"
  override protected lazy val tpeDbLocalDateTime : String = "VARCHAR"
  override protected lazy val tpeDbOffsetTime    : String = "VARCHAR"
  override protected lazy val tpeDbOffsetDateTime: String = "VARCHAR"
  override protected lazy val tpeDbZonedDateTime : String = "VARCHAR"
  override protected lazy val tpeDbUUID          : String = "UUID"
  override protected lazy val tpeDbURI           : String = "VARCHAR"
  override protected lazy val tpeDbByte          : String = "SMALLINT"
  override protected lazy val tpeDbShort         : String = "SMALLINT"
  override protected lazy val tpeDbChar          : String = "CHAR"
}
