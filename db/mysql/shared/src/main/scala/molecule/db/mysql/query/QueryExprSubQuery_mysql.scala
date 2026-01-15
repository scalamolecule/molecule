package molecule.db.mysql.query

import molecule.core.dataModel.*
import molecule.db.common.query.*
import molecule.db.common.query.casting.strategy.CastTuple

trait QueryExprSubQuery_mysql
  extends QueryExprSubQuery { self: Model2Query & QueryExprRef & SqlQueryBase =>

  override protected def createSubQueryBuilder(subElements: List[Element]): Model2SqlQuery & SqlQueryBase =
    new Model2SqlQuery_mysql(subElements)

  // Override to handle multiple-column SELECT subqueries
  // MySQL doesn't support ROW types in SELECT clause, so generate multiple scalar subqueries
  override protected def querySubQuery(subElements: List[Element], optLimit: Option[Int], optOffset: Option[Int], isJoin: Boolean): Unit = {
    if (shouldSplitSubquery(subElements, isJoin)) {
      querySplitSubQueries(subElements, optLimit, optOffset)
    } else {
      querySubQueryBase(subElements, optLimit, optOffset, isJoin)
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

  // MySQL-specific: build subquery with just the single attribute + tacit/ref elements for correlation
  override protected def buildSubqueryElements(otherElements: List[Element], attr: Attr): List[Element] = {
    otherElements :+ attr
  }

  // MySQL-specific: turn off DISTINCT for SELECT clause correlated subqueries with aggregates
  override protected def configureSubQueryBuilder(
    subQueryBuilder: Model2SqlQuery with SqlQueryBase,
    isImplicit: Boolean,
    isJoin: Boolean
  ): Unit = {
    if (!isJoin && !isImplicit && subQueryBuilder.aggregate) {
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
