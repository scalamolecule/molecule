package molecule.sql.core.query

import java.net.URI
import java.util.{Date, UUID}

trait LambdasOne extends LambdasBase { self: SqlQueryBase =>

  case class ResOne[T](
    tpe: String,
    sql2one: (Row, ParamIndex) => T,
    sql2oneOrNull: (Row, ParamIndex) => Any, // Allow null in optional nested rows
    one2sql: T => String,
    array2set: (Row, Int) => AnyRef,
    json2tpe: String => T,
    json2array: String => Array[T]
  )

  protected lazy val sql2oneString    : (Row, Int) => String     = (row: Row, paramIndex: Int) => row.getString(paramIndex)
  protected lazy val sql2oneInt       : (Row, Int) => Int        = (row: Row, paramIndex: Int) => row.getInt(paramIndex)
  protected lazy val sql2oneLong      : (Row, Int) => Long       = (row: Row, paramIndex: Int) => row.getLong(paramIndex)
  protected lazy val sql2oneFloat     : (Row, Int) => Float      = (row: Row, paramIndex: Int) => row.getFloat(paramIndex)
  protected lazy val sql2oneDouble    : (Row, Int) => Double     = (row: Row, paramIndex: Int) => row.getDouble(paramIndex)
  protected lazy val sql2oneBoolean   : (Row, Int) => Boolean    = (row: Row, paramIndex: Int) => row.getBoolean(paramIndex)
  protected lazy val sql2oneBigInt    : (Row, Int) => BigInt     = (row: Row, paramIndex: Int) => row.getBigDecimal(paramIndex).toBigInteger
  protected lazy val sql2oneBigDecimal: (Row, Int) => BigDecimal = (row: Row, paramIndex: Int) => row.getBigDecimal(paramIndex)
  protected lazy val sql2oneDate      : (Row, Int) => Date       = (row: Row, paramIndex: Int) => row.getDate(paramIndex)
  protected lazy val sql2oneUUID      : (Row, Int) => UUID       = (row: Row, paramIndex: Int) => UUID.fromString(row.getString(paramIndex))
  protected lazy val sql2oneURI       : (Row, Int) => URI        = (row: Row, paramIndex: Int) => new URI(row.getString(paramIndex))
  protected lazy val sql2oneByte      : (Row, Int) => Byte       = (row: Row, paramIndex: Int) => row.getByte(paramIndex)
  protected lazy val sql2oneShort     : (Row, Int) => Short      = (row: Row, paramIndex: Int) => row.getShort(paramIndex)
  protected lazy val sql2oneChar      : (Row, Int) => Char       = (row: Row, paramIndex: Int) => row.getString(paramIndex).charAt(0)

  protected lazy val resString1    : ResOne[String]     = ResOne("String", sql2oneString, sql2oneStringOrNull, one2sqlString, array2setString, json2oneString, json2arrayString)
  protected lazy val resInt1       : ResOne[Int]        = ResOne("Int", sql2oneInt, sql2oneIntOrNull, one2sqlInt, array2setInt, json2oneInt, json2arrayInt)
  protected lazy val resLong1      : ResOne[Long]       = ResOne("Long", sql2oneLong, sql2oneLongOrNull, one2sqlLong, array2setLong, json2oneLong, json2arrayLong)
  protected lazy val resFloat1     : ResOne[Float]      = ResOne("Float", sql2oneFloat, sql2oneFloatOrNull, one2sqlFloat, array2setFloat, json2oneFloat, json2arrayFloat)
  protected lazy val resDouble1    : ResOne[Double]     = ResOne("Double", sql2oneDouble, sql2oneDoubleOrNull, one2sqlDouble, array2setDouble, json2oneDouble, json2arrayDouble)
  protected lazy val resBoolean1   : ResOne[Boolean]    = ResOne("Boolean", sql2oneBoolean, sql2oneBooleanOrNull, one2sqlBoolean, array2setBoolean, json2oneBoolean, json2arrayBoolean)
  protected lazy val resBigInt1    : ResOne[BigInt]     = ResOne("BigInt", sql2oneBigInt, sql2oneBigIntOrNull, one2sqlBigInt, array2setBigInt, json2oneBigInt, json2arrayBigInt)
  protected lazy val resBigDecimal1: ResOne[BigDecimal] = ResOne("BigDecimal", sql2oneBigDecimal, sql2oneBigDecimalOrNull, one2sqlBigDecimal, array2setBigDecimal, json2oneBigDecimal, json2arrayBigDecimal)
  protected lazy val resDate1      : ResOne[Date]       = ResOne("Date", sql2oneDate, sql2oneDateOrNull, one2sqlDate, array2setDate, json2oneDate, json2arrayDate)
  protected lazy val resUUID1      : ResOne[UUID]       = ResOne("UUID", sql2oneUUID, sql2oneUUIDOrNull, one2sqlUUID, array2setUUID, json2oneUUID, json2arrayUUID)
  protected lazy val resURI1       : ResOne[URI]        = ResOne("URI", sql2oneURI, sql2oneURIOrNull, one2sqlURI, array2setURI, json2oneURI, json2arrayURI)
  protected lazy val resByte1      : ResOne[Byte]       = ResOne("Byte", sql2oneByte, sql2oneByteOrNull, one2sqlByte, array2setByte, json2oneByte, json2arrayByte)
  protected lazy val resShort1     : ResOne[Short]      = ResOne("Short", sql2oneShort, sql2oneShortOrNull, one2sqlShort, array2setShort, json2oneShort, json2arrayShort)
  protected lazy val resChar1      : ResOne[Char]       = ResOne("Char", sql2oneChar, sql2oneCharOrNull, one2sqlChar, array2setChar, json2oneChar, json2arrayChar)

  protected lazy val sql2oneStringOrNull    : (Row, Int) => Any = { (row: Row, paramIndex: Int) => val v = row.getString(paramIndex); if (row.wasNull()) null else v }
  protected lazy val sql2oneIntOrNull       : (Row, Int) => Any = { (row: Row, paramIndex: Int) => val v = row.getInt(paramIndex); if (row.wasNull()) null else v }
  protected lazy val sql2oneLongOrNull      : (Row, Int) => Any = { (row: Row, paramIndex: Int) => val v = row.getLong(paramIndex); if (row.wasNull()) null else v }
  protected lazy val sql2oneFloatOrNull     : (Row, Int) => Any = { (row: Row, paramIndex: Int) => val v = row.getFloat(paramIndex); if (row.wasNull()) null else v }
  protected lazy val sql2oneDoubleOrNull    : (Row, Int) => Any = { (row: Row, paramIndex: Int) => val v = row.getDouble(paramIndex); if (row.wasNull()) null else v }
  protected lazy val sql2oneBooleanOrNull   : (Row, Int) => Any = { (row: Row, paramIndex: Int) => val v = row.getBoolean(paramIndex); if (row.wasNull()) null else v }
  protected lazy val sql2oneBigIntOrNull    : (Row, Int) => Any = { (row: Row, paramIndex: Int) => val v = row.getBigDecimal(paramIndex); if (row.wasNull()) null else BigInt(v.toBigInteger) }
  protected lazy val sql2oneBigDecimalOrNull: (Row, Int) => Any = { (row: Row, paramIndex: Int) => val v = row.getBigDecimal(paramIndex); if (row.wasNull()) null else BigDecimal(v) }
  protected lazy val sql2oneDateOrNull      : (Row, Int) => Any = { (row: Row, paramIndex: Int) => val v = row.getDate(paramIndex); if (row.wasNull()) null else v }
  protected lazy val sql2oneUUIDOrNull      : (Row, Int) => Any = { (row: Row, paramIndex: Int) => val v = row.getString(paramIndex); if (row.wasNull()) null else UUID.fromString(v) }
  protected lazy val sql2oneURIOrNull       : (Row, Int) => Any = { (row: Row, paramIndex: Int) => val v = row.getString(paramIndex); if (row.wasNull()) null else new URI(v) }
  protected lazy val sql2oneByteOrNull      : (Row, Int) => Any = { (row: Row, paramIndex: Int) => val v = row.getByte(paramIndex); if (row.wasNull()) null else v }
  protected lazy val sql2oneShortOrNull     : (Row, Int) => Any = { (row: Row, paramIndex: Int) => val v = row.getShort(paramIndex); if (row.wasNull()) null else v }
  protected lazy val sql2oneCharOrNull      : (Row, Int) => Any = { (row: Row, paramIndex: Int) => val v = row.getString(paramIndex); if (row.wasNull()) null else v.charAt(0) }

  case class ResOneOpt[T](
    tpe: String,
    sql2oneOpt: (Row, ParamIndex) => Option[T],
    one2sql: T => String
  )

  lazy val resOptString    : ResOneOpt[String]     = ResOneOpt("String", sql2oneOptString, one2sqlString)
  lazy val resOptInt       : ResOneOpt[Int]        = ResOneOpt("Int", sql2oneOptInt, one2sqlInt)
  lazy val resOptLong      : ResOneOpt[Long]       = ResOneOpt("Long", sql2oneOptLong, one2sqlLong)
  lazy val resOptFloat     : ResOneOpt[Float]      = ResOneOpt("Float", sql2oneOptFloat, one2sqlFloat)
  lazy val resOptDouble    : ResOneOpt[Double]     = ResOneOpt("Double", sql2oneOptDouble, one2sqlDouble)
  lazy val resOptBoolean   : ResOneOpt[Boolean]    = ResOneOpt("Boolean", sql2oneOptBoolean, one2sqlBoolean)
  lazy val resOptBigInt    : ResOneOpt[BigInt]     = ResOneOpt("BigInt", sql2oneOptBigInt, one2sqlBigInt)
  lazy val resOptBigDecimal: ResOneOpt[BigDecimal] = ResOneOpt("BigDecimal", sql2oneOptBigDecimal, one2sqlBigDecimal)
  lazy val resOptDate      : ResOneOpt[Date]       = ResOneOpt("Date", sql2oneOptDate, one2sqlDate)
  lazy val resOptUUID      : ResOneOpt[UUID]       = ResOneOpt("UUID", sql2oneOptUUID, one2sqlUUID)
  lazy val resOptURI       : ResOneOpt[URI]        = ResOneOpt("URI", sql2oneOptURI, one2sqlURI)
  lazy val resOptByte      : ResOneOpt[Byte]       = ResOneOpt("Byte", sql2oneOptByte, one2sqlByte)
  lazy val resOptShort     : ResOneOpt[Short]      = ResOneOpt("Short", sql2oneOptShort, one2sqlShort)
  lazy val resOptChar      : ResOneOpt[Char]       = ResOneOpt("Char", sql2oneOptChar, one2sqlChar)


  protected lazy val sql2oneOptString    : (Row, Int) => Option[String]     = (row: Row, paramIndex: Int) => {
    val v = row.getString(paramIndex)
    if (row.wasNull()) Option.empty[String] else Some(v)
  }
  protected lazy val sql2oneOptInt       : (Row, Int) => Option[Int]        = (row: Row, paramIndex: Int) => {
    val v = row.getInt(paramIndex)
    if (row.wasNull()) Option.empty[Int] else Some(v)
  }
  protected lazy val sql2oneOptLong      : (Row, Int) => Option[Long]       = (row: Row, paramIndex: Int) => {
    val v = row.getLong(paramIndex)
    if (row.wasNull()) Option.empty[Long] else Some(v)
  }
  protected lazy val sql2oneOptFloat     : (Row, Int) => Option[Float]      = (row: Row, paramIndex: Int) => {
    val v = row.getFloat(paramIndex)
    if (row.wasNull()) Option.empty[Float] else Some(v)
  }
  protected lazy val sql2oneOptDouble    : (Row, Int) => Option[Double]     = (row: Row, paramIndex: Int) => {
    val v = row.getDouble(paramIndex)
    if (row.wasNull()) Option.empty[Double] else Some(v)
  }
  protected lazy val sql2oneOptBoolean   : (Row, Int) => Option[Boolean]    = (row: Row, paramIndex: Int) => {
    val v = row.getBoolean(paramIndex)
    if (row.wasNull()) Option.empty[Boolean] else Some(v)
  }
  protected lazy val sql2oneOptBigInt    : (Row, Int) => Option[BigInt]     = (row: Row, paramIndex: Int) => {
    val v = row.getBigDecimal(paramIndex)
    if (row.wasNull()) Option.empty[BigInt] else Some(v.toBigInteger)
  }
  protected lazy val sql2oneOptBigDecimal: (Row, Int) => Option[BigDecimal] = (row: Row, paramIndex: Int) => {
    val v = row.getBigDecimal(paramIndex)
    if (row.wasNull()) Option.empty[BigDecimal] else Some(v)
  }
  protected lazy val sql2oneOptDate      : (Row, Int) => Option[Date]       = (row: Row, paramIndex: Int) => {
    val v = row.getDate(paramIndex)
    if (row.wasNull()) Option.empty[Date] else Some(v)
  }
  protected lazy val sql2oneOptUUID      : (Row, Int) => Option[UUID]       = (row: Row, paramIndex: Int) => {
    val v = row.getString(paramIndex)
    if (row.wasNull()) Option.empty[UUID] else Some(UUID.fromString(v))
  }
  protected lazy val sql2oneOptURI       : (Row, Int) => Option[URI]        = (row: Row, paramIndex: Int) => {
    val v = row.getString(paramIndex)
    if (row.wasNull()) Option.empty[URI] else Some(new URI(v))
  }
  protected lazy val sql2oneOptByte      : (Row, Int) => Option[Byte]       = (row: Row, paramIndex: Int) => {
    val v = row.getByte(paramIndex)
    if (row.wasNull()) Option.empty[Byte] else Some(v)
  }
  protected lazy val sql2oneOptShort     : (Row, Int) => Option[Short]      = (row: Row, paramIndex: Int) => {
    val v = row.getShort(paramIndex)
    if (row.wasNull()) Option.empty[Short] else Some(v)
  }
  protected lazy val sql2oneOptChar      : (Row, Int) => Option[Char]       = (row: Row, paramIndex: Int) => {
    val v = row.getString(paramIndex)
    if (row.wasNull()) Option.empty[Char] else Some(v.charAt(0))
  }
}