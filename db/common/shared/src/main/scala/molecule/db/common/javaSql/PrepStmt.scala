package molecule.db.common.javaSql

import java.sql.{Date, Time, Timestamp}

trait PrepStmt {

  def setNull(parameterIndex: Int, sqlType: Int): Unit
  def setBoolean(parameterIndex: Int, x: Boolean): Unit
  def setByte(parameterIndex: Int, x: Byte): Unit
  def setShort(parameterIndex: Int, x: Short): Unit
  def setInt(parameterIndex: Int, x: Int): Unit
  def setLong(parameterIndex: Int, x: Long): Unit
  def setFloat(parameterIndex: Int, x: Float): Unit
  def setDouble(parameterIndex: Int, x: Double): Unit
  def setBigDecimal(parameterIndex: Int, x: java.math.BigDecimal): Unit
  def setString(parameterIndex: Int, x: String): Unit
  def setBytes(parameterIndex: Int, x: Array[Byte]): Unit
  def setDate(parameterIndex: Int, x: Date): Unit
  def setTime(parameterIndex: Int, x: Time): Unit
  def setTimestamp(parameterIndex: Int, x: Timestamp): Unit
}