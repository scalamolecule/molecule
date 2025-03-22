package molecule.sql.core.javaSql

import java.math.{BigDecimal => jBigDecimal}
import java.net.URL
import java.sql.ResultSet

class ResultSetImpl(val underlying: ResultSet) extends ResultSetInterface {

  override def next(): Boolean = underlying.next
  override def close(): Unit = underlying.close()
  override def wasNull(): Boolean = underlying.wasNull
  override def setFetchSize(size: Int): Unit = underlying.setFetchSize(size)

  override def getString(columnIndex: Int): String = underlying.getString(columnIndex)
  override def getBoolean(columnIndex: Int): Boolean = underlying.getBoolean(columnIndex)
  override def getByte(columnIndex: Int): Byte = underlying.getByte(columnIndex)
  override def getShort(columnIndex: Int): Short = underlying.getShort(columnIndex)
  override def getInt(columnIndex: Int): Int = underlying.getInt(columnIndex)
  override def getLong(columnIndex: Int): Long = underlying.getLong(columnIndex)
  override def getFloat(columnIndex: Int): Float = underlying.getFloat(columnIndex)
  override def getDouble(columnIndex: Int): Double = underlying.getDouble(columnIndex)
  override def getBytes(columnIndex: Int): Array[Byte] = underlying.getBytes(columnIndex)
  override def getBigDecimal(columnIndex: Int): jBigDecimal = underlying.getBigDecimal(columnIndex)
  override def getURL(columnIndex: Int): URL = underlying.getURL(columnIndex)
  override def getObject(columnIndex: Int): AnyRef = underlying.getObject(columnIndex)

  override def getArray(columnIndex: Int): ArrayInterface =
    new ArrayImpl(underlying.getArray(columnIndex))

  override def isBeforeFirst: Boolean = underlying.isBeforeFirst
  override def isAfterLast: Boolean = underlying.isAfterLast
  override def isFirst: Boolean = underlying.isFirst
  override def isLast: Boolean = underlying.isLast
  override def beforeFirst(): Unit = underlying.beforeFirst()
  override def afterLast(): Unit = underlying.afterLast()
  override def first(): Boolean = underlying.first
  override def last(): Boolean = underlying.last
  override def getRow: Int = underlying.getRow
  override def previous(): Boolean = underlying.previous
  override def isClosed: Boolean = underlying.isClosed

  override def insertRow(): Unit = underlying.insertRow()
  override def updateRow(): Unit = underlying.updateRow()
  override def deleteRow(): Unit = underlying.deleteRow()
  override def refreshRow(): Unit = underlying.refreshRow()
  override def cancelRowUpdates(): Unit = underlying.cancelRowUpdates()
  override def moveToInsertRow(): Unit = underlying.moveToInsertRow()
  override def moveToCurrentRow(): Unit = underlying.moveToCurrentRow()
}
