package molecule.db.mariadb.query

import molecule.core.dataModel.*
import molecule.db.common.query.*
import molecule.db.common.query.casting.CastTpl_
import molecule.db.common.query.casting.strategy.CastTuple

trait QueryExprSubQuery_mariadb
  extends QueryExprSubQuery { self: Model2Query & QueryExprRef & SqlQueryBase =>

  override protected def createSubQueryBuilder(subElements: List[Element]): Model2SqlQuery & SqlQueryBase =
    new Model2SqlQuery_mariadb(subElements)

  // Override to handle per-entity LIMIT using window functions
  // MariaDB doesn't support LATERAL keyword, so we use ROW_NUMBER() window functions
  override protected def buildSubQuerySqlWithCasts(
    subElements: List[Element],
    subQueryAlias: String,
    optLimit: Option[Int],
    optOffset: Option[Int],
    isImplicit: Boolean,
    isJoin: Boolean
  ): (String, List[Cast]) = {
    // For JOIN subqueries with LIMIT, use window functions for per-entity limiting
    // BUT only if there's a correlation attribute (otherwise it's a global limit)
    val hasCorrelation = subElements.exists {
      case a: Attr if a.filterAttr.isDefined => true
      case _ => false
    }

    if (isJoin && (optLimit.isDefined || optOffset.isDefined) && hasCorrelation) {
      buildWindowFunctionSubQuery(subElements, subQueryAlias, optLimit, optOffset)
    } else {
      super.buildSubQuerySqlWithCasts(subElements, subQueryAlias, optLimit, optOffset, isImplicit, isJoin)
    }
  }

  // Build subquery using ROW_NUMBER() window function for per-entity LIMIT
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

    val casts = wrapMultiColumnCasts(subqueryCasts, isJoin = true)
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
        .flatMap { case (_, _, col, dir) =>
          // Skip the MariaDB NULL handling expressions like "(columnName IS NULL)"
          // We only want the actual column ordering
          if (col.startsWith("(") && col.contains("IS")) {
            None // Skip NULL handling helper clauses
          } else {
            // col might be "Table.column" or just "column"
            // Need to strip table prefix and reference via inner_query alias
            val columnName = if (col.contains(".")) {
              col.split("\\.").last
            } else {
              col
            }
            Some(s"inner_query.$columnName$dir")
          }
        }
      if (orderClauses.isEmpty) "" else " ORDER BY " + orderClauses.mkString(", ")
    }
  }

  // Override to handle multiple-column SELECT subqueries and per-entity LIMIT for joins
  // MariaDB doesn't support ROW types in SELECT clause, so generate multiple scalar subqueries
  override protected def querySubQuery(
    subElements: List[Element],
    optLimit: Option[Int],
    optOffset: Option[Int],
    isJoin: Boolean
  ): Unit = {
    val hasCorrelation = subElements.exists {
      case a: Attr if a.filterAttr.isDefined => true
      case _ => false
    }

    if (shouldSplitSubquery(subElements, isJoin)) {
      querySplitSubQueries(subElements, optLimit, optOffset)
    } else if (isJoin && (optLimit.isDefined || optOffset.isDefined) && hasCorrelation) {
      // Use window function approach for per-entity limiting in JOIN subqueries
      queryWindowFunctionJoin(subElements, optLimit, optOffset)
    } else {
      querySubQueryBase(subElements, optLimit, optOffset, isJoin)
    }
  }

  private def queryWindowFunctionJoin(
    subElements: List[Element],
    optLimit: Option[Int],
    optOffset: Option[Int]
  ): Unit = {
    subQueryIndex += 1
    val alias = subQueryAlias

    val wasInsideSubQuery = insideSubQuery
    insideSubQuery = true

    val (subquerySql, subQueryCasts) = buildWindowFunctionSubQuery(subElements, alias, optLimit, optOffset)

    // Use INNER JOIN with ON clause for window function results
    val joinConditions = extractJoinConditions(subElements, alias)
    if (joinConditions.nonEmpty) {
      val onClause = joinConditions.mkString(" AND ")
      from += s" INNER JOIN $subquerySql $alias ON $onClause"
    } else {
      from += s" CROSS JOIN $subquerySql $alias"
    }

    // Add subquery columns to outer SELECT
    subElements.foreach {
      case a: Attr if !a.isInstanceOf[Tacit] =>
        val columnRef = s"$alias.${a.attr}"
        select += columnRef

        a.sort.foreach { sort =>
          val (dir, arity) = (sort.head, sort.substring(1, 2).toInt)
          val sortDir = if (dir == 'a') "" else " DESC"
          orderBy += ((level, arity, columnRef, sortDir))
        }
      case _ => ()
    }

    // Add subquery column casts
    if (subQueryCasts.length > 1) {
      castStrategy.add((row: RS, startIndex: Int) => CastTpl_.cast(subQueryCasts, startIndex)(row))
    } else {
      subQueryCasts.foreach(castStrategy.add)
    }

    insideSubQuery = wasInsideSubQuery
  }

  private def extractJoinConditions(subElements: List[Element], subqueryAlias: String): List[String] = {
    subElements.flatMap {
      case a: Attr if a.filterAttr.isDefined =>
        a.filterAttr.map {
          case (_, filterPath, filterAttr) =>
            val outerTable = filterPath.lastOption.getOrElse(filterAttr.ent)
            val outerCol = filterAttr.attr
            val subqueryCol = a.attr
            s"$outerTable.$outerCol = $subqueryAlias.$subqueryCol"
        }
      case _ => None
    }
  }

  override protected def shouldSplitSubquery(subElements: List[Element], isJoin: Boolean): Boolean = {
    if (isJoin) {
      false
    } else {
      val nonTacitAttrs = subElements.collect { case a: Attr if !a.isInstanceOf[Tacit] => a }
      nonTacitAttrs.length > 1
    }
  }

  // MariaDB-specific: add NULL handling for sorted attributes in split subqueries
  override protected def handleAttrSorting(attr: Attr, allAttrs: List[Attr]): Unit = {
    attr.sort.foreach { sort =>
      val (dir, arity)   = (sort.head, sort.substring(1, 2).toInt)
      val selectPosition = select.length.toString
      // MariaDB doesn't support NULLS FIRST/LAST, simulate it
      dir match {
        case 'a' =>
          // ASC NULLS FIRST: sort NULLs first, then values ascending
          orderBy += ((level, arity, s"($selectPosition IS NOT NULL)", ""))
          orderBy += ((level, arity, selectPosition, ""))
        case 'd' =>
          // DESC NULLS LAST: sort values descending, then NULLs last
          orderBy += ((level, arity, s"($selectPosition IS NULL)", ""))
          orderBy += ((level, arity, selectPosition, " DESC"))
      }
    }
  }

  // MariaDB-specific: turn off DISTINCT for SELECT clause correlated subqueries with aggregates
  override protected def configureSubQueryBuilder(
    subQueryBuilder: Model2SqlQuery & SqlQueryBase,
    isImplicit: Boolean,
    isJoin: Boolean
  ): Unit = {
    if (!isJoin && !isImplicit && subQueryBuilder.aggregate) {
      subQueryBuilder.distinct = false
    }
  }
}
