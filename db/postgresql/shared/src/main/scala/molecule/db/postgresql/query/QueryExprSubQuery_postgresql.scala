package molecule.db.postgresql.query

import molecule.core.dataModel.*
import molecule.db.common.query.*
import molecule.db.common.query.casting.strategy.CastTuple

trait QueryExprSubQuery_postgresql
  extends QueryExprSubQuery { self: Model2Query & QueryExprRef & SqlQueryBase =>

  override protected def createSubQueryBuilder(subElements: List[Element]): Model2SqlQuery & SqlQueryBase =
    new Model2SqlQuery_postgresql(subElements)

  // Override to split multi-column SELECT subqueries when aggregating IDs or sorting
  // This allows aggregating IDs across relationships which doesn't work with ROW types
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
      val hasAggregation = nonTacitAttrs.exists(attr => attr.op.isInstanceOf[AggrFn])
      val hasSorting = nonTacitAttrs.exists(_.sort.isDefined)
      nonTacitAttrs.length > 1 && (hasAggregation || hasSorting)
    }
  }

  // PostgreSQL-specific: return casts directly without wrapping
  override protected def wrapMultiColumnCasts(
    subqueryCasts: List[Cast],
    isJoin: Boolean
  ): List[Cast] = {
    subqueryCasts
  }
}
