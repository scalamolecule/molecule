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

  override protected def shouldSplitSubquery(subElements: List[Element], isJoin: Boolean): Boolean = {
    if (isJoin) {
      false
    } else {
      val nonTacitAttrs    = subElements.collect { case a: Attr if !a.isInstanceOf[Tacit] => a }
      val hasIdAggregation = nonTacitAttrs.exists(attr => attr.name == "id" && attr.op.isInstanceOf[AggrFn])
      val hasSorting       = nonTacitAttrs.exists(_.sort.isDefined)
      nonTacitAttrs.length > 1 && (hasIdAggregation || hasSorting)
    }
  }

  // H2-specific cast handling for multi-column subqueries
  override protected def wrapMultiColumnCasts(
    subqueryCasts: List[Cast],
    isJoin: Boolean
  ): List[Cast] = {
    if (subqueryCasts.length > 1) {
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
  }

  // Handle JOIN subquery columns (separate values)
  protected def castJoinSubqueryTuple(columnCasts: List[Cast]): Cast = {
    val cast = (startIndex: Int) => CastTpl_.cast(columnCasts, startIndex)
    (row: RS, startIndex: Int) => cast(startIndex)(row)
  }

  // Handle H2's ROW type (SELECT clause subqueries)
  protected def castSubqueryRow(rowCasts: List[Cast]): Cast = {
    // Starting at index 1 since SQL column indices are 1-based
    val cast   = CastTpl_.cast(rowCasts, 1)
    val row2rs = h2Row2resultSet(rowCasts)
    (row: RS, paramIndex: Int) => cast(row2rs(row, paramIndex))
  }
}
