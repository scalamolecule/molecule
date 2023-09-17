package molecule.sql.core.query

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.util.BaseHelpers


trait ResolveBase extends BaseHelpers { self: SqlQueryBase =>

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


  lazy val toInt: (Row, Int) => Int = (row: Row, n: Int) => row.getLong(n).toInt

  protected def sqlArray2set[T](row: Row, n: Int, getValue: Row => T): Set[T] = {
    val array = row.getArray(n)
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

}