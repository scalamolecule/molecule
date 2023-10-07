package molecule.sql.core.query

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.util.BaseHelpers
import molecule.core.util.AggrUtils


trait LambdasBase extends BaseHelpers with AggrUtils { self: SqlQueryBase =>

  protected lazy val one2sqlString    : String => String     = (v: String) => s"'${v.replace("'", "''")}'"
  protected lazy val one2sqlInt       : Int => String        = (v: Int) => s"$v"
  protected lazy val one2sqlLong      : Long => String       = (v: Long) => s"$v"
  protected lazy val one2sqlFloat     : Float => String      = (v: Float) => s"$v"
  protected lazy val one2sqlDouble    : Double => String     = (v: Double) => s"$v"
  protected lazy val one2sqlBoolean   : Boolean => String    = (v: Boolean) => s"$v"
  protected lazy val one2sqlBigInt    : BigInt => String     = (v: BigInt) => s"$v"
  protected lazy val one2sqlBigDecimal: BigDecimal => String = (v: BigDecimal) => s"$v"
  protected lazy val one2sqlDate      : Date => String       = (v: Date) => s"'${date2str(v)}'"
  protected lazy val one2sqlUUID      : UUID => String       = (v: UUID) => s"'${v.toString}'"
  protected lazy val one2sqlURI       : URI => String        = (v: URI) => s"'${v.toString.replace("'", "''")}'"
  protected lazy val one2sqlByte      : Byte => String       = (v: Byte) => s"$v"
  protected lazy val one2sqlShort     : Short => String      = (v: Short) => s"$v"
  protected lazy val one2sqlChar      : Char => String       = (v: Char) => s"'${v.toString}'"


  lazy val toInt: (Row, Int) => Int = (row: Row, paramIndex: Int) => row.getLong(paramIndex).toInt

  protected def sqlArray2set[T](row: Row, paramIndex: Int, getValue: Row => T): Set[T] = {
    val array = row.getArray(paramIndex)
    if (row.wasNull()) {
      Set.empty[T]
    } else {
      val arrayResultSet = array.getResultSet
      var set            = Set.empty[T]
      while (arrayResultSet.next()) {
        set += getValue(arrayResultSet)
      }
      set
    }
  }

  protected lazy val valueString    : Row => String     = (rs: Row) => rs.getString(2)
  protected lazy val valueInt       : Row => Int        = (rs: Row) => rs.getInt(2)
  protected lazy val valueLong      : Row => Long       = (rs: Row) => rs.getLong(2)
  protected lazy val valueFloat     : Row => Float      = (rs: Row) => rs.getFloat(2)
  protected lazy val valueDouble    : Row => Double     = (rs: Row) => rs.getDouble(2)
  protected lazy val valueBoolean   : Row => Boolean    = (rs: Row) => rs.getBoolean(2)
  protected lazy val valueBigInt    : Row => BigInt     = (rs: Row) => rs.getBigDecimal(2).toBigInteger
  protected lazy val valueBigDecimal: Row => BigDecimal = (rs: Row) => rs.getBigDecimal(2)
  protected lazy val valueDate      : Row => Date       = (rs: Row) => rs.getDate(2)
  protected lazy val valueUUID      : Row => UUID       = (rs: Row) => UUID.fromString(rs.getString(2))
  protected lazy val valueURI       : Row => URI        = (rs: Row) => new URI(rs.getString(2))
  protected lazy val valueByte      : Row => Byte       = (rs: Row) => rs.getByte(2)
  protected lazy val valueShort     : Row => Short      = (rs: Row) => rs.getShort(2)
  protected lazy val valueChar      : Row => Char       = (rs: Row) => rs.getString(2).charAt(0)


  protected lazy val array2setString    : (Row, Int) => Set[String]     = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueString)
  protected lazy val array2setInt       : (Row, Int) => Set[Int]        = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueInt)
  protected lazy val array2setLong      : (Row, Int) => Set[Long]       = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueLong)
  protected lazy val array2setFloat     : (Row, Int) => Set[Float]      = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueFloat)
  protected lazy val array2setDouble    : (Row, Int) => Set[Double]     = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueDouble)
  protected lazy val array2setBoolean   : (Row, Int) => Set[Boolean]    = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueBoolean)
  protected lazy val array2setBigInt    : (Row, Int) => Set[BigInt]     = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueBigInt)
  protected lazy val array2setBigDecimal: (Row, Int) => Set[BigDecimal] = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueBigDecimal)
  protected lazy val array2setDate      : (Row, Int) => Set[Date]       = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueDate)
  protected lazy val array2setUUID      : (Row, Int) => Set[UUID]       = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueUUID)
  protected lazy val array2setURI       : (Row, Int) => Set[URI]        = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueURI)
  protected lazy val array2setByte      : (Row, Int) => Set[Byte]       = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueByte)
  protected lazy val array2setShort     : (Row, Int) => Set[Short]      = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueShort)
  protected lazy val array2setChar      : (Row, Int) => Set[Char]       = (row: Row, paramIndex: Int) => sqlArray2set(row, paramIndex, valueChar)


  protected lazy val json2oneString    : String => String     = (v: String) => v
  protected lazy val json2oneInt       : String => Int        = (v: String) => v.toInt
  protected lazy val json2oneLong      : String => Long       = (v: String) => v.toLong
  protected lazy val json2oneFloat     : String => Float      = (v: String) => v.toFloat
  protected lazy val json2oneDouble    : String => Double     = (v: String) => v.toDouble
  protected lazy val json2oneBoolean   : String => Boolean    = (v: String) => v == "1"
  protected lazy val json2oneBigInt    : String => BigInt     = (v: String) => BigInt(v)
  protected lazy val json2oneBigDecimal: String => BigDecimal = (v: String) => BigDecimal(v)
  protected lazy val json2oneDate      : String => Date       = (v: String) => str2date(v)
  protected lazy val json2oneUUID      : String => UUID       = (v: String) => UUID.fromString(v)
  protected lazy val json2oneURI       : String => URI        = (v: String) => new URI(v)
  protected lazy val json2oneByte      : String => Byte       = (v: String) => v.toByte
  protected lazy val json2oneShort     : String => Short      = (v: String) => v.toShort
  protected lazy val json2oneChar      : String => Char       = (v: String) => v.charAt(0)

  protected lazy val json2arrayString    : String => Array[String]     = (json: String) => json.substring(2, json.length - 2).split("\", ?\"").map(json2oneString)
  protected lazy val json2arrayInt       : String => Array[Int]        = (json: String) => json.substring(1, json.length - 1).split(", ?").map(json2oneInt)
  protected lazy val json2arrayLong      : String => Array[Long]       = (json: String) => json.substring(1, json.length - 1).split(", ?").map(json2oneLong)
  protected lazy val json2arrayFloat     : String => Array[Float]      = (json: String) => json.substring(1, json.length - 1).split(", ?").map(json2oneFloat)
  protected lazy val json2arrayDouble    : String => Array[Double]     = (json: String) => json.substring(1, json.length - 1).split(", ?").map(json2oneDouble)
  protected lazy val json2arrayBoolean   : String => Array[Boolean]    = (json: String) => json.substring(1, json.length - 1).split(", ?").map(json2oneBoolean)
  protected lazy val json2arrayBigInt    : String => Array[BigInt]     = (json: String) => json.substring(1, json.length - 1).split(", ?").map(json2oneBigInt)
  protected lazy val json2arrayBigDecimal: String => Array[BigDecimal] = (json: String) => json.substring(1, json.length - 1).split(", ?").map(json2oneBigDecimal)
  protected lazy val json2arrayDate      : String => Array[Date]       = (json: String) => json.substring(2, json.length - 2).replace("000000", "000").split("\", ?\"").map(json2oneDate)
  protected lazy val json2arrayUUID      : String => Array[UUID]       = (json: String) => json.substring(2, json.length - 2).split("\", ?\"").map(json2oneUUID)
  protected lazy val json2arrayURI       : String => Array[URI]        = (json: String) => json.substring(2, json.length - 2).split("\", ?\"").map(json2oneURI)
  protected lazy val json2arrayByte      : String => Array[Byte]       = (json: String) => json.substring(1, json.length - 1).split(", ?").map(json2oneByte)
  protected lazy val json2arrayShort     : String => Array[Short]      = (json: String) => json.substring(1, json.length - 1).split(", ?").map(json2oneShort)
  protected lazy val json2arrayChar      : String => Array[Char]       = (json: String) => json.substring(2, json.length - 2).split("\", ?\"").map(json2oneChar)


  protected lazy val one2jsonString    : String => String     = (v: String) => "\"" + escStr(v) + "\""
  protected lazy val one2jsonInt       : Int => String        = (v: Int) => s"$v"
  protected lazy val one2jsonLong      : Long => String       = (v: Long) => s"$v"
  protected lazy val one2jsonFloat     : Float => String      = (v: Float) => s"$v"
  protected lazy val one2jsonDouble    : Double => String     = (v: Double) => s"$v"
  protected lazy val one2jsonBoolean   : Boolean => String    = (v: Boolean) => if (v) "1" else "0"
  protected lazy val one2jsonBigInt    : BigInt => String     = (v: BigInt) => s"$v"
  protected lazy val one2jsonBigDecimal: BigDecimal => String = (v: BigDecimal) => s"$v"
  protected lazy val one2jsonDate      : Date => String       = (v: Date) => "\"" + date2str(v) + "\""
  protected lazy val one2jsonUUID      : UUID => String       = (v: UUID) => "\"" + v.toString + "\""
  protected lazy val one2jsonURI       : URI => String        = (v: URI) => "\"" + v.toString.replace("'", "''") + "\""
  protected lazy val one2jsonByte      : Byte => String       = (v: Byte) => s"$v"
  protected lazy val one2jsonShort     : Short => String      = (v: Short) => s"$v"
  protected lazy val one2jsonChar      : Char => String       = (v: Char) => "\"" + v.toString + "\""

  protected lazy val tpeDbString    : String = ""
  protected lazy val tpeDbInt       : String = ""
  protected lazy val tpeDbLong      : String = ""
  protected lazy val tpeDbFloat     : String = ""
  protected lazy val tpeDbDouble    : String = ""
  protected lazy val tpeDbBoolean   : String = ""
  protected lazy val tpeDbBigInt    : String = ""
  protected lazy val tpeDbBigDecimal: String = ""
  protected lazy val tpeDbDate      : String = ""
  protected lazy val tpeDbUUID      : String = ""
  protected lazy val tpeDbURI       : String = ""
  protected lazy val tpeDbByte      : String = ""
  protected lazy val tpeDbShort     : String = ""
  protected lazy val tpeDbChar      : String = ""
}