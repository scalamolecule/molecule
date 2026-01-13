package molecule.db.h2.query

import molecule.core.dataModel.Element
import molecule.db.common.javaSql.ResultSetInterface

class Model2SqlQuery_h2_JVM(elements0: List[Element]) extends Model2SqlQuery_h2(elements0) {

  // JVM-specific: Override to handle JOIN subquery columns (separate values)
  override protected def castJoinSubqueryTuple(columnCasts: List[Cast]): Cast = {
    (row: RS, startIndex: Int) => {
      // Apply each cast to consecutive columns, starting from startIndex
      val values = columnCasts.zipWithIndex.map { case (cast, offset) =>
        cast(row, startIndex + offset)
      }
      // Return as tuple based on arity
      values.length match {
        case 2  => (values(0), values(1))
        case 3  => (values(0), values(1), values(2))
        case 4  => (values(0), values(1), values(2), values(3))
        case 5  => (values(0), values(1), values(2), values(3), values(4))
        case 6  => (values(0), values(1), values(2), values(3), values(4), values(5))
        case 7  => (values(0), values(1), values(2), values(3), values(4), values(5), values(6))
        case 8  => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7))
        case 9  => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8))
        case 10 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9))
        case 11 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10))
        case 12 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11))
        case 13 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12))
        case 14 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13))
        case 15 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13), values(14))
        case 16 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13), values(14), values(15))
        case 17 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13), values(14), values(15), values(16))
        case 18 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13), values(14), values(15), values(16), values(17))
        case 19 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13), values(14), values(15), values(16), values(17), values(18))
        case 20 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13), values(14), values(15), values(16), values(17), values(18), values(19))
        case 21 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13), values(14), values(15), values(16), values(17), values(18), values(19), values(20))
        case 22 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13), values(14), values(15), values(16), values(17), values(18), values(19), values(20), values(21))
        case _  => throw new IllegalArgumentException(s"Unsupported tuple arity: ${values.length}")
      }
    }
  }

  // JVM-specific: Override to handle H2's ROW type (SELECT clause subqueries)
  override protected def castSubqueryRow(rowCasts: List[Cast]): Cast = {
    (row: RS, paramIndex: Int) => {
      // Get the raw ROW object from H2
      val rowObject = row.getObject(paramIndex)

      // Convert H2's ROW to a ResultSet that we can apply casts to
      val rowResultSet = convertH2RowToResultSet(rowObject)

      val values = rowCasts.zipWithIndex.map { case (cast, idx) =>
        cast(rowResultSet, idx + 1)
      }
      // Return as tuple based on arity
      values.length match {
        case 2  => (values(0), values(1))
        case 3  => (values(0), values(1), values(2))
        case 4  => (values(0), values(1), values(2), values(3))
        case 5  => (values(0), values(1), values(2), values(3), values(4))
        case 6  => (values(0), values(1), values(2), values(3), values(4), values(5))
        case 7  => (values(0), values(1), values(2), values(3), values(4), values(5), values(6))
        case 8  => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7))
        case 9  => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8))
        case 10 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9))
        case 11 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10))
        case 12 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11))
        case 13 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12))
        case 14 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13))
        case 15 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13), values(14))
        case 16 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13), values(14), values(15))
        case 17 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13), values(14), values(15), values(16))
        case 18 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13), values(14), values(15), values(16), values(17))
        case 19 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13), values(14), values(15), values(16), values(17), values(18))
        case 20 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13), values(14), values(15), values(16), values(17), values(18), values(19))
        case 21 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13), values(14), values(15), values(16), values(17), values(18), values(19), values(20))
        case 22 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13), values(14), values(15), values(16), values(17), values(18), values(19), values(20), values(21))
        case _  => throw new IllegalArgumentException(s"Unsupported tuple arity: ${values.length}")
      }
    }
  }

  // H2-specific: Convert H2's ROW type to a ResultSet we can query
  private def convertH2RowToResultSet(obj: AnyRef): RS = obj match {
    case rs: java.sql.ResultSet =>
      // H2 returns ROW as a ResultSet - position cursor to first row
      rs.next()
      // Wrap it in our ResultSetImpl
      new molecule.db.common.javaSql.ResultSetImpl(rs)
    case valueRow: org.h2.value.ValueRow =>
      // If we get a ValueRow directly, use synthetic ResultSet
      new RowResultSet_h2_JVM(valueRow)
    case other =>
      throw new IllegalArgumentException(
        s"Unsupported H2 ROW type: ${other.getClass.getName}. " +
        s"Aggregating on related entities in subqueries is not currently supported."
      )
  }
}

// Synthetic ResultSet that wraps H2's ValueRow fields
private class RowResultSet_h2_JVM(valueRow: org.h2.value.ValueRow) extends ResultSetInterface {
  private val fields = valueRow.getList

  override def getString(columnIndex: Int): String =
    fields(columnIndex - 1).getString
  override def getBoolean(columnIndex: Int): Boolean =
    fields(columnIndex - 1).getBoolean
  override def getByte(columnIndex: Int): Byte =
    fields(columnIndex - 1).getByte
  override def getShort(columnIndex: Int): Short =
    fields(columnIndex - 1).getShort
  override def getInt(columnIndex: Int): Int =
    fields(columnIndex - 1).getInt
  override def getLong(columnIndex: Int): Long =
    fields(columnIndex - 1).getLong
  override def getFloat(columnIndex: Int): Float =
    fields(columnIndex - 1).getFloat
  override def getDouble(columnIndex: Int): Double =
    fields(columnIndex - 1).getDouble
  override def getBytes(columnIndex: Int): Array[Byte] =
    fields(columnIndex - 1).getBytes
  override def getBigDecimal(columnIndex: Int): java.math.BigDecimal =
    fields(columnIndex - 1).getBigDecimal
  override def getURL(columnIndex: Int): java.net.URL =
    new java.net.URL(fields(columnIndex - 1).getString)
  override def getObject(columnIndex: Int): AnyRef =
    throw new UnsupportedOperationException("getObject not supported on ROW ResultSet - use typed getters")

  // Unsupported operations for synthetic ResultSet
  override def next(): Boolean = false
  override def close(): Unit = ()
  override def wasNull(): Boolean = false
  override def setFetchSize(size: Int): Unit = ()
  override def getArray(columnIndex: Int): molecule.db.common.javaSql.ArrayInterface =
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

