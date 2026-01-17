package molecule.db.h2.query

import molecule.core.dataModel.*
import molecule.db.common.javaSql.ResultSetInterface
import molecule.db.common.query.*
import molecule.db.common.query.casting.*
import molecule.db.common.query.casting.strategy.CastTuple

trait QueryExprSubQuery_h2
  extends QueryExprSubQuery { self: Model2Query & QueryExprRef & SqlQueryBase =>

  override protected def createSubQueryBuilder(subElements: List[Element]): Model2SqlQuery & SqlQueryBase =
    new Model2SqlQuery_h2(subElements)

  // Override to handle per-entity LIMIT using window functions
  // H2 doesn't support LATERAL keyword, so we use ROW_NUMBER() window functions
  override protected def buildSubQuerySqlWithCasts(
    subElements: List[Element],
    subQueryAlias: String,
    optLimit: Option[Int],
    optOffset: Option[Int],
    isImplicit: Boolean
  ): (String, List[Cast]) = {
    // For JOIN subqueries with LIMIT, use window functions for per-entity limiting
    // BUT only if there's a correlation attribute (otherwise it's a global limit)
    val hasCorrelation = subElements.exists {
      case a: Attr if a.filterAttr.isDefined => true
      case _ => false
    }

    if ((optLimit.isDefined || optOffset.isDefined) && hasCorrelation) {
      buildWindowFunctionSubQuery(subElements, subQueryAlias, optLimit, optOffset)
    } else {
      super.buildSubQuerySqlWithCasts(subElements, subQueryAlias, optLimit, optOffset, isImplicit)
    }
  }

  // Build subquery using ROW_NUMBER() window function for per-entity LIMIT
  // This is kept as a fallback approach if LATERAL doesn't work
  private def buildWindowFunctionSubQuery(
    subElements: List[Element],
    subQueryAlias: String,
    optLimit: Option[Int],
    optOffset: Option[Int]
  ): (String, List[Cast]) = {
    val subQueryBuilder = createSubQueryBuilder(subElements)
    subQueryBuilder.insideSubQuery = true
    subQueryBuilder.insideJoinSubQuery = true
    subQueryBuilder.resolveElements(subElements)

    if (subQueryBuilder.hasManSubQueryAttr) {
      hasManSubQueryAttr = true
    }

    // Extract partition column (the join/filter column)
    val partitionCol = extractPartitionColumn(subElements)

    // Get ALL attributes (including tacit ones needed for join conditions)
    val selectColumns = subElements.collect {
      case a: Attr => a.attr
    }.mkString(", ")

    // Build the inner query (without LIMIT)
    val innerSql = subQueryBuilder.renderSubQuery(3, Some("inner_query"), None, None, isImplicit = false)

    // Build ORDER BY clause for window function from subquery's orderBy
    val windowOrderBy = buildWindowOrderBy(subQueryBuilder)

    // Build row number filter based on limit/offset
    val rowFilter = (optOffset, optLimit) match {
      case (None, None)                => "" // Should not happen, but handle gracefully
      case (None, Some(limit))         => s"rn <= $limit"
      case (Some(offset), None)        => s"rn > $offset"
      case (Some(offset), Some(limit)) => s"rn > $offset AND rn <= ${offset + limit}"
    }

    // Build the complete window function query
    // Need to wrap in outer SELECT to filter by row number and exclude rn column
    val windowSql = s"""(
      |    SELECT DISTINCT
      |      $selectColumns
      |    FROM (
      |      SELECT DISTINCT
      |        inner_query.*,
      |        ROW_NUMBER() OVER (PARTITION BY inner_query.$partitionCol$windowOrderBy) as rn
      |      FROM $innerSql inner_query
      |    ) filtered_query
      |    WHERE $rowFilter
      |  )""".stripMargin

    val subqueryCasts = subQueryBuilder.castStrategy match {
      case tuple: CastTuple => tuple.getCasts
      case _                => Nil
    }

    val casts = wrapMultiColumnCasts(subqueryCasts)
    (windowSql, casts)
  }

  // Extract the partition column from filter attributes
  private def extractPartitionColumn(subElements: List[Element]): String = {
    subElements.collectFirst {
      case a: Attr if a.filterAttr.isDefined =>
        // The column we're joining on (e.g., "entity" in Ref.entity_)
        a.attr
    }.getOrElse {
      // Uncorrelated subquery - use any column for partitioning (all rows will be in same partition)
      subElements.collectFirst {
        case a: Attr => a.attr
      }.getOrElse("id")
    }
  }

  // Build ORDER BY clause for window function
  private def buildWindowOrderBy(subQueryBuilder: Model2SqlQuery & SqlQueryBase): String = {
    if (subQueryBuilder.orderBy.isEmpty) {
      ""
    } else {
      val orderClauses = subQueryBuilder.orderBy.toList
        .sortBy(_._2) // Sort by arity
        .map { case (_, _, col, dir) =>
          // col might be "Table.column" or just "column"
          // Need to strip table prefix and reference via inner_query alias
          val columnName = if (col.contains(".")) {
            col.split("\\.").last
          } else {
            col
          }
          s"inner_query.$columnName$dir"
        }
      " ORDER BY " + orderClauses.mkString(", ")
    }
  }

  // Override to split multi-column SELECT subqueries when aggregating IDs or sorting
  // This allows aggregating IDs across relationships which doesn't work with ROW types
  override protected def querySubQuery(
    subElements: List[Element],
    optLimit: Option[Int],
    optOffset: Option[Int]
  ): Unit = {
    if (shouldSplitSubquery(subElements)) {
      querySplitSubQueries(subElements, optLimit, optOffset)
    } else {
      querySubQueryBase(subElements, optLimit, optOffset)
    }
  }

  override protected def shouldSplitSubquery(subElements: List[Element]): Boolean = {
    // JOIN subqueries never split
    false
  }

  // H2-specific cast handling for multi-column subqueries
  override protected def wrapMultiColumnCasts(
    subqueryCasts: List[Cast]
  ): List[Cast] = {
    if (subqueryCasts.length > 1) {
      // JOIN: columns are separate in result set, group into tuple manually
      List(castJoinSubqueryTuple(subqueryCasts))
    } else {
      subqueryCasts
    }
  }

  // Handle JOIN subquery columns (separate values)
  protected def castJoinSubqueryTuple(columnCasts: List[Cast]): Cast = {
    (row: RS, startIndex: Int) => CastTpl_.cast(columnCasts, startIndex)(row)
  }

  // Handle H2's ROW type (SELECT clause subqueries)
  protected def castSubqueryRow(rowCasts: List[Cast]): Cast = {
    // Starting at index 1 since SQL column indices are 1-based
    val cast   = CastTpl_.cast(rowCasts, 1)
    val row2rs = h2Row2resultSet(rowCasts)
    (row: RS, paramIndex: Int) => cast(row2rs(row, paramIndex))
  }
}
