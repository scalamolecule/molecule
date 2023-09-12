package molecule.sql.core.javaSql

import java.net.URL
import java.util.Date


trait ResultSetInterface {

  def next(): Boolean
  def close(): Unit
  def wasNull(): Boolean

  // Methods for accessing results by column index// Methods for accessing results by column index

  def getString(columnIndex: Int): String
  def getBoolean(columnIndex: Int): Boolean
  def getByte(columnIndex: Int): Byte
  def getShort(columnIndex: Int): Short
  def getInt(columnIndex: Int): Int
  def getLong(columnIndex: Int): Long
  def getFloat(columnIndex: Int): Float
  def getDouble(columnIndex: Int): Double
  def getBytes(columnIndex: Int): Array[Byte]
  def getDate(columnIndex: Int): Date
  def getBigDecimal(columnIndex: Int): java.math.BigDecimal
  def getURL(columnIndex: Int): URL

  //  def getTime(columnIndex: Int): Time
  //  def getTimestamp(columnIndex: Int): Timestamp

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