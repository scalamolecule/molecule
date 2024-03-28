package molecule.sql.mariadb.query

import java.util.Date
import molecule.sql.core.query.{LambdasSeq, SqlQueryBase}

trait LambdasSeq_mariadb extends LambdasSeq { self: SqlQueryBase =>

  override protected lazy val tpeDbId            : String = "BIGINT"
  override protected lazy val tpeDbString        : String = "LONGTEXT"
  override protected lazy val tpeDbInt           : String = "INT"
  override protected lazy val tpeDbLong          : String = "BIGINT"
  override protected lazy val tpeDbFloat         : String = "REAL"
  override protected lazy val tpeDbDouble        : String = "DOUBLE"
  override protected lazy val tpeDbBoolean       : String = "TINYINT(1)"
  override protected lazy val tpeDbBigInt        : String = "DECIMAL(65, 0)"
  override protected lazy val tpeDbBigDecimal    : String = "DECIMAL(65, 30)"
  override protected lazy val tpeDbDate          : String = "BIGINT"
  override protected lazy val tpeDbDuration      : String = "TEXT"
  override protected lazy val tpeDbInstant       : String = "TEXT"
  override protected lazy val tpeDbLocalDate     : String = "TEXT"
  override protected lazy val tpeDbLocalTime     : String = "TEXT"
  override protected lazy val tpeDbLocalDateTime : String = "TEXT"
  override protected lazy val tpeDbOffsetTime    : String = "TEXT"
  override protected lazy val tpeDbOffsetDateTime: String = "TEXT"
  override protected lazy val tpeDbZonedDateTime : String = "TEXT"
  override protected lazy val tpeDbUUID          : String = "TINYTEXT"
  override protected lazy val tpeDbURI           : String = "TEXT"
  override protected lazy val tpeDbByte          : String = "TINYINT"
  override protected lazy val tpeDbShort         : String = "SMALLINT"
  override protected lazy val tpeDbChar          : String = "CHAR"


  override protected lazy val valueDate     : RS => Date            = (rs: RS) => new Date(rs.getLong(2))
  override protected lazy val json2oneDate  : String => Date        = (v: String) => new Date(v.toLong)
  override protected lazy val one2jsonDate  : Date => String        = (v: Date) => s"${v.getTime}"
  override protected lazy val json2arrayDate: String => Array[Date] = (json: String) => json.substring(1, json.length - 1).split(", ?").map(json2oneDate)
  override protected lazy val one2sqlDate   : Date => String        = (v: Date) => s"${v.getTime}"

  override protected lazy val one2sqlBoolean: Boolean => String = (v: Boolean) => if (v) "1" else "0"
}
