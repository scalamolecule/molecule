package molecule.db.sqlite.query

import molecule.core.dataModel.*
import molecule.db.common.query.*
import molecule.db.common.query.casting.strategy.CastTuple

trait QueryExprSubQuery_sqlite
  extends QueryExprSubQuery { self: Model2Query & QueryExprRef & SqlQueryBase =>

  override protected def createSubQueryBuilder(subElements: List[Element]): Model2SqlQuery & SqlQueryBase =
    new Model2SqlQuery_sqlite(subElements)

  // Override to handle multiple-column SELECT subqueries
  // SQLite doesn't support ROW types, so generate multiple scalar subqueries
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
}
