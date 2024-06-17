package molecule.sql.core.javaSql

import java.sql.{Date, PreparedStatement, Time, Timestamp}
import java.math.{BigDecimal => jBigDecimal}


class PrepStmtImpl(val underlying: PreparedStatement) extends PrepStmt {

  override def setNull(parameterIndex: Int, sqlType: Int): Unit = underlying.setNull(parameterIndex, sqlType)
  override def setBoolean(parameterIndex: Int, x: Boolean): Unit = underlying.setBoolean(parameterIndex, x)
  override def setByte(parameterIndex: Int, x: Byte): Unit = underlying.setByte(parameterIndex, x)
  override def setShort(parameterIndex: Int, x: Short): Unit = underlying.setShort(parameterIndex, x)
  override def setInt(parameterIndex: Int, x: Int): Unit = underlying.setInt(parameterIndex, x)
  override def setLong(parameterIndex: Int, x: Long): Unit = underlying.setLong(parameterIndex, x)
  override def setFloat(parameterIndex: Int, x: Float): Unit = underlying.setFloat(parameterIndex, x)
  override def setDouble(parameterIndex: Int, x: Double): Unit = underlying.setDouble(parameterIndex, x)
  override def setBigDecimal(parameterIndex: Int, x: jBigDecimal): Unit = underlying.setBigDecimal(parameterIndex, x)
  override def setString(parameterIndex: Int, x: String): Unit = underlying.setString(parameterIndex, x)
  override def setBytes(parameterIndex: Int, x: Array[Byte]): Unit = underlying.setBytes(parameterIndex, x)
  override def setDate(parameterIndex: Int, x: Date): Unit = underlying.setDate(parameterIndex, x)
  override def setTime(parameterIndex: Int, x: Time): Unit = underlying.setTime(parameterIndex, x)
  override def setTimestamp(parameterIndex: Int, x: Timestamp): Unit = underlying.setTimestamp(parameterIndex, x)

}
