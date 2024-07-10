package molecule.sql.core.javaSql

import java.net.URL

trait ResultSetInterface {

  def next(): Boolean
  def close(): Unit
  def wasNull(): Boolean

  def getString(columnIndex: Int): String
  def getBoolean(columnIndex: Int): Boolean
  def getByte(columnIndex: Int): Byte
  def getShort(columnIndex: Int): Short
  def getInt(columnIndex: Int): Int
  def getLong(columnIndex: Int): Long
  def getFloat(columnIndex: Int): Float
  def getDouble(columnIndex: Int): Double
  def getBytes(columnIndex: Int): Array[Byte]
  def getBigDecimal(columnIndex: Int): java.math.BigDecimal
  def getURL(columnIndex: Int): URL
  def getObject(columnIndex: Int): AnyRef


  def getArray(columnIndex: Int): ArrayInterface

  def isBeforeFirst: Boolean
  def isAfterLast: Boolean
  def isFirst: Boolean
  def isLast: Boolean
  def beforeFirst(): Unit
  def afterLast(): Unit
  def first(): Boolean
  def last(): Boolean
  def getRow: Int
  def previous(): Boolean
  def isClosed: Boolean

  def insertRow(): Unit
  def updateRow(): Unit
  def deleteRow(): Unit
  def refreshRow(): Unit
  def cancelRowUpdates(): Unit
  def moveToInsertRow(): Unit
  def moveToCurrentRow(): Unit
}