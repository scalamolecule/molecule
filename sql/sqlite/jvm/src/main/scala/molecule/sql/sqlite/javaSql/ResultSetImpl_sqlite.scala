package molecule.sql.sqlite.javaSql

import java.math.{BigDecimal => jBigDecimal}
import java.net.URL
import java.sql.ResultSet
import molecule.sql.core.javaSql.{ArrayImpl, ArrayInterface, ResultSetInterface}

class ResultSetImpl_sqlite(val underlying: ResultSet) extends ResultSetInterface {

  // Cache small result sets in pre-allocated Array

  // Most queries will return less than this number of rows - make configurable
  private val smallSize   = 100
  private var rows        = new Array[Array[AnyRef]](smallSize)
  private var smallResult = true

  private val colCount     = underlying.getMetaData.getColumnCount
  private val rowSize      = colCount + 1
  private var rowIndex     = 0
  private var prevColIndex = -1

  // Load data into pre-allocated array cache
  while (smallResult && underlying.next) {
    // index 0 not used to ease column 1-indexing access
    val row = new Array[AnyRef](rowSize)
    for (i <- 1 to colCount) {
      val v: AnyRef = underlying.getObject(i) // already of correct jdbc type
      row(i) = v
    }
    rows(rowIndex) = row
    rowIndex += 1
    if (rowIndex == smallSize) {
      // Continue in next loop by growing array instead
      smallResult = false
    }
  }

  // Grow size of Array for bigger result sets
  while (!smallResult && underlying.next) {
    // index 0 not used to ease column 1-indexing access
    val row = new Array[AnyRef](rowSize)
    for (i <- 1 to colCount) {
      val v: AnyRef = underlying.getObject(i) // already of correct jdbc type
      row(i) = v
    }
    rows = rows :+ row
    rowIndex += 1
  }

  //  println("......")
  //  rows.foreach(println)
  //  println("......")

  val totalRowCount = rowIndex

  // Move row index/cursor to start (before first row) after loading has completed
  rowIndex = -1

  def value(columnIndex: Int) = {
    prevColIndex = columnIndex
    rows(rowIndex)(columnIndex)
  }


  // Data ---------------------------------------------

  override def getString(columnIndex: Int): String = value(columnIndex) match {
    case s: String            => s
    case i: java.lang.Integer => i.toString
    case d: java.lang.Double  => d.toString
  }
  override def getBoolean(columnIndex: Int): Boolean = value(columnIndex).asInstanceOf[Boolean]
  override def getByte(columnIndex: Int): Byte = value(columnIndex).asInstanceOf[Byte]
  override def getShort(columnIndex: Int): Short = value(columnIndex).asInstanceOf[Short]
  override def getInt(columnIndex: Int): Int = value(columnIndex).asInstanceOf[Int]
  override def getLong(columnIndex: Int): Long =
    value(columnIndex) match {
      case l: java.lang.Long    => l.longValue()
      case i: java.lang.Integer => i.longValue()
    }
  override def getFloat(columnIndex: Int): Float = value(columnIndex).asInstanceOf[Double].toFloat
  override def getDouble(columnIndex: Int): Double = value(columnIndex).asInstanceOf[Double]
  override def getBytes(columnIndex: Int): Array[Byte] = value(columnIndex).asInstanceOf[Array[Byte]]
  override def getBigDecimal(columnIndex: Int): jBigDecimal = value(columnIndex).asInstanceOf[jBigDecimal]
  override def getURL(columnIndex: Int): URL = value(columnIndex).asInstanceOf[URL]

  override def getArray(columnIndex: Int): ArrayInterface =
    new ArrayImpl(value(columnIndex).asInstanceOf[java.sql.Array])


  // Cursor/actions -----------------------------------

  override def next(): Boolean = {
    rowIndex += 1
    rowIndex != totalRowCount
  }
  override def close(): Unit = underlying.close()
  override def wasNull(): Boolean = value(prevColIndex) == null
  override def isBeforeFirst: Boolean = rowIndex == -1
  override def isAfterLast: Boolean = rowIndex == totalRowCount
  override def isFirst: Boolean = rowIndex == 0
  override def isLast: Boolean = rowIndex == totalRowCount - 1
  override def beforeFirst(): Unit = rowIndex = -1
  override def afterLast(): Unit = rowIndex = totalRowCount
  override def first(): Boolean = {
    rowIndex = -1
    true
  }
  override def last(): Boolean = {
    rowIndex = totalRowCount - 1
    true
  }
  override def getRow: Int = rowIndex + 1
  override def previous(): Boolean = {
    rowIndex -= 1
    true
  }
  override def isClosed: Boolean = underlying.isClosed

  override def insertRow(): Unit = underlying.insertRow()
  override def updateRow(): Unit = underlying.updateRow()
  override def deleteRow(): Unit = underlying.deleteRow()
  override def refreshRow(): Unit = underlying.refreshRow()
  override def cancelRowUpdates(): Unit = underlying.cancelRowUpdates()
  override def moveToInsertRow(): Unit = underlying.moveToInsertRow()
  override def moveToCurrentRow(): Unit = underlying.moveToCurrentRow()
}
