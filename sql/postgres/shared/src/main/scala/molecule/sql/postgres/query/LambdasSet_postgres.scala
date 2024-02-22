package molecule.sql.postgres.query

import molecule.sql.core.query.{LambdasSet, SqlQueryBase}

trait LambdasSet_postgres extends LambdasSet { self: SqlQueryBase =>

//  override protected lazy val j2Float: Any => Float = (v: Any) => v.toString.toFloat
//  override protected lazy val j2Byte : Any => Byte  = (v: Any) => v.asInstanceOf[Short].toByte
//  override protected lazy val j2Short: Any => Short = (v: Any) => v.asInstanceOf[Short]

//  protected lazy val j2Float: Any => Float = (v: Any) => v.toString.toFloat
//  protected lazy val j2Byte : Any => Byte  = (v: Any) => v.asInstanceOf[Short].toByte
//  protected lazy val j2Short: Any => Short = (v: Any) => v.asInstanceOf[Short]



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
