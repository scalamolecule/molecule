package molecule.db.h2.javaSql

import java.math.BigDecimal as jBigDecimal
import java.net.{URL, URI}
import molecule.db.common.javaSql.{ArrayInterface, ResultSetInterface}

// Synthetic ResultSet that wraps H2's ValueRow fields
class ResultSetImpl_h2(valueRow: org.h2.value.ValueRow) extends ResultSetInterface {
  private val fields = valueRow.getList

  override def getString(columnIndex: Int): String = fields(columnIndex - 1).getString
  override def getBoolean(columnIndex: Int): Boolean = fields(columnIndex - 1).getBoolean
  override def getByte(columnIndex: Int): Byte = fields(columnIndex - 1).getByte
  override def getShort(columnIndex: Int): Short = fields(columnIndex - 1).getShort
  override def getInt(columnIndex: Int): Int = fields(columnIndex - 1).getInt
  override def getLong(columnIndex: Int): Long = fields(columnIndex - 1).getLong
  override def getFloat(columnIndex: Int): Float = fields(columnIndex - 1).getFloat
  override def getDouble(columnIndex: Int): Double = fields(columnIndex - 1).getDouble
  override def getBytes(columnIndex: Int): Array[Byte] = fields(columnIndex - 1).getBytes
  override def getBigDecimal(columnIndex: Int): jBigDecimal = fields(columnIndex - 1).getBigDecimal
  override def getURL(columnIndex: Int): URL =  new URI(fields(columnIndex - 1).getString).toURL
  override def getObject(columnIndex: Int): AnyRef =
    throw new UnsupportedOperationException("getObject not supported on ROW ResultSet - use typed getters")

  // Unsupported operations for synthetic ResultSet
  override def next(): Boolean = false
  override def close(): Unit = ()
  override def wasNull(): Boolean = false
  override def setFetchSize(size: Int): Unit = ()
  override def getArray(columnIndex: Int): ArrayInterface =
    throw new UnsupportedOperationException("getArray not supported on ROW ResultSet")
  override def isBeforeFirst: Boolean = false
  override def isAfterLast: Boolean = false
  override def isFirst: Boolean = false
  override def isLast: Boolean = false
  override def beforeFirst(): Unit = ()
  override def afterLast(): Unit = ()
  override def first(): Boolean = false
  override def last(): Boolean = false
  override def getRow: Int = 0
  override def previous(): Boolean = false
  override def isClosed: Boolean = false
  override def insertRow(): Unit = ()
  override def updateRow(): Unit = ()
  override def deleteRow(): Unit = ()
  override def refreshRow(): Unit = ()
  override def cancelRowUpdates(): Unit = ()
  override def moveToInsertRow(): Unit = ()
  override def moveToCurrentRow(): Unit = ()
}
