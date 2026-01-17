package molecule.db.mysql.query

import molecule.core.dataModel.*
import molecule.db.common.query.*
import molecule.db.common.query.casting.strategy.CastTuple

trait QueryExprSubQuery_mysql
  extends QueryExprSubQuery { self: Model2Query & QueryExprRef & SqlQueryBase =>

  override protected def createSubQueryBuilder(subElements: List[Element]): Model2SqlQuery & SqlQueryBase =
    new Model2SqlQuery_mysql(subElements)

  // Override to handle per-entity LIMIT using LATERAL JOIN
  // MySQL 8.0.14+ supports LATERAL keyword for efficient correlated joins
  override protected def querySubQueryBase(
    subElements: List[Element],
    optLimit: Option[Int],
    optOffset: Option[Int]
  ): Unit = {
    if (optLimit.isDefined || optOffset.isDefined) {
      // Use LATERAL join for per-entity limiting
      queryLateralJoin(subElements, optLimit, optOffset)
    } else {
      super.querySubQueryBase(subElements, optLimit, optOffset)
    }
  }

  private def queryLateralJoin(
    subElements: List[Element],
    optLimit: Option[Int],
    optOffset: Option[Int]
  ): Unit = {
    subQueryIndex += 1
    val alias = subQueryAlias

    val wasInsideSubQuery = insideSubQuery
    insideSubQuery = true

    // Build the correlated subquery with LIMIT/OFFSET
    val (subquerySql, subQueryCasts) = buildLateralSubQuery(subElements, alias, optLimit, optOffset)

    // MySQL: Use LEFT JOIN LATERAL with ON true
    // The correlation happens via the filter attribute in the subquery's WHERE clause
    from += s" LEFT JOIN LATERAL $subquerySql $alias ON true"

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
    subQueryCasts.foreach(castStrategy.add)

    insideSubQuery = wasInsideSubQuery
  }

  private def buildLateralSubQuery(
    subElements: List[Element],
    subQueryAlias: String,
    optLimit: Option[Int],
    optOffset: Option[Int]
  ): (String, List[Cast]) = {
    val subQueryBuilder = createSubQueryBuilder(subElements)
    subQueryBuilder.insideSubQuery = true
    // Don't set insideJoinSubQuery - we WANT the WHERE clause for correlation
    // insideJoinSubQuery = false means WHERE correlation will be included
    subQueryBuilder.resolveElements(subElements)

    if (subQueryBuilder.hasManSubQueryAttr) {
      hasManSubQueryAttr = true
    }

    // Build the inner query with LIMIT/OFFSET applied directly
    // The correlation happens via the filter attribute (e.g., entity_(Entity.id_))
    val innerSql = subQueryBuilder.renderSubQuery(2, Some(subQueryAlias), optLimit, optOffset, isImplicit = false)

    val subqueryCasts = subQueryBuilder.castStrategy match {
      case tuple: CastTuple => tuple.getCasts
      case _                => Nil
    }

    (innerSql, subqueryCasts)
  }

  // Override to handle multiple-column SELECT subqueries
  // MySQL doesn't support ROW types in SELECT clause, so generate multiple scalar subqueries
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

  // MySQL-specific: build subquery with just the single attribute + tacit/ref elements for correlation
  override protected def buildSubqueryElements(otherElements: List[Element], attr: Attr): List[Element] = {
    otherElements :+ attr
  }

  // MySQL-specific: turn off DISTINCT for correlated subqueries with aggregates
  override protected def configureSubQueryBuilder(
    subQueryBuilder: Model2SqlQuery & SqlQueryBase,
    isImplicit: Boolean
  ): Unit = {
    if (!isImplicit && subQueryBuilder.aggregate) {
      subQueryBuilder.distinct = false
    }
  }

  // MySQL-specific: MySQL doesn't support NULLS FIRST/LAST, simulate it
  override protected def handleAttrSorting(attr: Attr, allAttrs: List[Attr]): Unit = {
    attr.sort.foreach { sort =>
      val (dir, arity)   = (sort.head, sort.substring(1, 2).toInt)
      val selectPosition = select.length.toString

      // In join subqueries with aggregates and GROUP BY, we can't use IS NULL checks
      // in ORDER BY due to sql_mode=only_full_group_by
      if (insideJoinSubQuery && aggregate) {
        // Just sort by the aggregate column directly
        val sortDir = if (dir == 'a') "" else " DESC"
        orderBy += ((level, arity, selectPosition, sortDir))
      } else {
        // MySQL doesn't support NULLS FIRST/LAST, simulate it
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
  }
}
