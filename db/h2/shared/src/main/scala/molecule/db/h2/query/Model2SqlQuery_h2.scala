package molecule.db.h2.query

import molecule.core.dataModel.*
import molecule.db.common.query.*
import molecule.db.common.query.casting.strategy.CastTuple

class Model2SqlQuery_h2(elements0: List[Element])
  extends Model2SqlQuery(elements0)
    with QueryExprOne_h2
    with QueryExprSet_h2
    with QueryExprSeq_h2
    with QueryExprMap_h2
    with QueryExprSetRefAttr_h2
    with SqlQueryBase {

  // Override to split multi-column SELECT subqueries when aggregating IDs or sorting
  // This allows aggregating IDs across relationships which doesn't work with ROW types
  override protected def querySubQuery(
    subElements: List[Element],
    optLimit: Option[Int],
    optOffset: Option[Int],
    isJoin: Boolean
  ): Unit = {
    if (shouldSplitSubquery(subElements, isJoin)) {
      querySplitSubQueries(subElements, optLimit, optOffset)
    } else {
      querySubQueryBase(subElements, optLimit, optOffset, isJoin)
    }
  }

  private def shouldSplitSubquery(subElements: List[Element], isJoin: Boolean): Boolean = {
    if (isJoin) {
      false
    } else {
      val nonTacitAttrs    = subElements.collect { case a: Attr if !a.isInstanceOf[Tacit] => a }
      val hasIdAggregation = nonTacitAttrs.exists(attr => attr.name == "id" && attr.op.isInstanceOf[AggrFn])
      val hasSorting       = nonTacitAttrs.exists(_.sort.isDefined)
      nonTacitAttrs.length > 1 && (hasIdAggregation || hasSorting)
    }
  }

  private def querySplitSubQueries(subElements: List[Element], optLimit: Option[Int], optOffset: Option[Int]): Unit = {
    val nonTacitAttrs = subElements.collect { case a: Attr if !a.isInstanceOf[Tacit] => a }
    val allCasts      = scala.collection.mutable.ListBuffer.empty[Cast]
    val otherElements = subElements.filter {
      case a: Attr if a.isInstanceOf[Tacit] => true // Tacit attrs for WHERE clauses
      case _: Ref                           => true // Ref elements for JOIN relationships
      case _                                => false
    }

    nonTacitAttrs.foreach { attr =>
      subQueryIndex += 1
      val alias = subQueryAlias

      val wasInsideSubQuery = insideSubQuery
      insideSubQuery = true

      // Build subquery with this attribute + all tacit/ref elements
      val subqueryElements             = otherElements :+ attr
      val (subquerySql, subQueryCasts) = buildSubQuerySqlWithCasts(
        subqueryElements, alias, optLimit, optOffset, isImplicit = false, isJoin = false
      )

      select += subquerySql
      allCasts ++= subQueryCasts

      // Propagate sorting if this attribute has sorting
      attr.sort.foreach { sort =>
        val (dir, arity)   = (sort.head, sort.substring(1, 2).toInt)
        val sortDir        = if (dir == 'a') "" else " DESC"
        val selectPosition = select.length.toString
        orderBy += ((level, arity, selectPosition, sortDir))
      }

      insideSubQuery = wasInsideSubQuery
    }

    // Group all casts into a single tuple cast
    val tupleCast = castMultipleColumns(allCasts.toList)
    castStrategy.add(tupleCast)
  }


  override protected def buildSubQuerySqlWithCasts(
    subElements: List[Element],
    subQueryAlias: String,
    optLimit: Option[Int],
    optOffset: Option[Int],
    isImplicit: Boolean,
    isJoin: Boolean
  ): (String, List[Cast]) = {
    // Create a new query builder instance for the subquery
    val subQueryBuilder = new Model2SqlQuery_h2(subElements)

    // Mark that we're inside a subquery (to allow ID filter attributes)
    subQueryBuilder.insideSubQuery = true

    // Mark if this is a JOIN subquery (affects correlation handling)
    subQueryBuilder.insideJoinSubQuery = isJoin

    // Resolve the subquery elements
    subQueryBuilder.resolveElements(subElements)

    // Propagate the flag back to main query if subquery has mandatory attributes
    if (subQueryBuilder.hasManSubQueryAttr) {
      hasManSubQueryAttr = true
    }

    // For SELECT clause subqueries (correlated, scalar), clear ORDER BY from subquery
    // UNLESS there's a LIMIT/OFFSET (ORDER BY determines which rows are selected)
    // The ordering will be applied to the outer query instead
    if (!isJoin && optLimit.isEmpty && optOffset.isEmpty) {
      subQueryBuilder.orderBy.clear()
    }

    // Get the SQL and casts
    val sql           = subQueryBuilder.renderSubQuery(2, Some(subQueryAlias), optLimit, optOffset, isImplicit)
    val subqueryCasts = subQueryBuilder.castStrategy match {
      case tuple: CastTuple => tuple.getCasts
      case _                => Nil
    }

    // Handle casts differently for JOIN vs SELECT subqueries
    val casts = if (subqueryCasts.length > 1) {
      if (isJoin) {
        // JOIN: columns are separate in result set, group into tuple manually
        List(castJoinSubqueryTuple(subqueryCasts))
      } else {
        // SELECT: H2 returns ROW type, extract values from ROW
        List(castSubqueryRow(subqueryCasts))
      }
    } else {
      subqueryCasts
    }

    (sql, casts)
  }

  // JVM-specific: Handle JOIN subquery columns (separate values)
  protected def castJoinSubqueryTuple(columnCasts: List[Cast]): Cast = {
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
        case _  => throw molecule.core.error.ModelError(s"Unsupported tuple arity: ${values.length}")
      }
    }
  }

  // JVM-specific: Handle H2's ROW type (SELECT clause subqueries)
  protected def castSubqueryRow(rowCasts: List[Cast]): Cast = {
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
        case _  => throw molecule.core.error.ModelError(s"Unsupported tuple arity: ${values.length}")
      }
    }
  }

  // H2-specific: Convert H2's ROW type to a ResultSet we can query
  private def convertH2RowToResultSet(obj: AnyRef): RS = obj match {
    case null                            =>
      throw molecule.core.error.ModelError("Null ROW from subquery - subquery returned no results")
    case rs: java.sql.ResultSet          =>
      // H2 returns ROW as a ResultSet - position cursor to first row
      rs.next()
      // Wrap it in our ResultSetImpl
      new molecule.db.common.javaSql.ResultSetImpl(rs)
    case valueRow: org.h2.value.ValueRow =>
      // If we get a ValueRow directly, use synthetic ResultSet
      new RowResultSet_h2(valueRow)
    case other                           =>
      throw molecule.core.error.ModelError(
        s"Unsupported H2 ROW type: ${other.getClass.getName}. " +
          s"Aggregating on related entities in subqueries is not currently supported."
      )
  }
}

// Synthetic ResultSet that wraps H2's ValueRow fields
private class RowResultSet_h2(valueRow: org.h2.value.ValueRow) extends molecule.db.common.javaSql.ResultSetInterface {
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
  override def getBigDecimal(columnIndex: Int): java.math.BigDecimal = fields(columnIndex - 1).getBigDecimal
  override def getURL(columnIndex: Int): java.net.URL = new java.net.URL(fields(columnIndex - 1).getString)
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