package molecule.db.mariadb.query

import molecule.core.dataModel.*
import molecule.db.common.query.*
import molecule.db.common.query.casting.strategy.CastTuple

trait QueryExprSubQuery_mariadb
  extends QueryExprSubQuery { self: Model2Query & QueryExprRef & SqlQueryBase =>

  override protected def createSubQueryBuilder(subElements: List[Element]): Model2SqlQuery & SqlQueryBase =
    new Model2SqlQuery_mariadb(subElements)

  // Override to handle multiple-column SELECT subqueries
  // MariaDB doesn't support ROW types in SELECT clause, so generate multiple scalar subqueries
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

  // MariaDB-specific: only sort on first attribute in multi-column subquery
  override protected def handleAttrSorting(attr: Attr, allAttrs: List[Attr]): Unit = {
    if (attr == allAttrs.head) {
      attr.sort.foreach { sort =>
        val (dir, arity)   = (sort.head, sort.substring(1, 2).toInt)
        val sortDir        = if (dir == 'a') "" else " DESC"
        val selectPosition = select.length.toString
        orderBy += ((level, arity, selectPosition, sortDir))
      }
    }
  }

  // MariaDB-specific: turn off DISTINCT for SELECT clause correlated subqueries with aggregates
  override protected def configureSubQueryBuilder(
    subQueryBuilder: Model2SqlQuery with SqlQueryBase,
    isImplicit: Boolean,
    isJoin: Boolean
  ): Unit = {
    if (!isJoin && !isImplicit && subQueryBuilder.aggregate) {
      subQueryBuilder.distinct = false
    }
  }
}
