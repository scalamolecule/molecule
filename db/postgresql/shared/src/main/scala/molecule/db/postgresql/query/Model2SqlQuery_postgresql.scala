package molecule.db.postgresql.query

import molecule.core.dataModel.*
import molecule.db.common.query.*
import molecule.db.common.query.casting.strategy.CastTuple

class Model2SqlQuery_postgresql(elements0: List[Element])
  extends Model2SqlQuery(elements0)
    with QueryExprOne_postgresql
    with QueryExprSet_postgresql
    with QueryExprSeq_postgresql
    with QueryExprMap_postgresql
    with QueryExprSetRefAttr_postgresql
    with SqlQueryBase {

  // Override to split multi-column SELECT subqueries when aggregating IDs or sorting
  // This allows aggregating IDs across relationships which doesn't work with ROW types
  override protected def querySubQuery(subElements: List[Element], optLimit: Option[Int], optOffset: Option[Int], isJoin: Boolean): Unit = {
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
      val nonTacitAttrs = subElements.collect { case a: Attr if !a.isInstanceOf[Tacit] => a }
      val hasAggregation = nonTacitAttrs.exists(attr => attr.op.isInstanceOf[AggrFn])
      val hasSorting = nonTacitAttrs.exists(_.sort.isDefined)
      nonTacitAttrs.length > 1 && (hasAggregation || hasSorting)
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
    subElements: List[Element], subQueryAlias: String, optLimit: Option[Int], optOffset: Option[Int],
    isImplicit: Boolean, isJoin: Boolean
  ): (String, List[Cast]) = {
    val subQueryBuilder = new Model2SqlQuery_postgresql(subElements)
    subQueryBuilder.insideSubQuery = true
    subQueryBuilder.insideJoinSubQuery = isJoin
    subQueryBuilder.resolveElements(subElements)
    if (subQueryBuilder.hasManSubQueryAttr) {
      hasManSubQueryAttr = true
    }
    // For SELECT clause subqueries (correlated, scalar), clear ORDER BY from subquery
    // UNLESS there's a LIMIT/OFFSET (ORDER BY determines which rows are selected)
    // The ordering will be applied to the outer query instead
    if (!isJoin && optLimit.isEmpty && optOffset.isEmpty) {
      subQueryBuilder.orderBy.clear()
    }
    val sql   = subQueryBuilder.renderSubQuery(2, Some(subQueryAlias), optLimit, optOffset, isImplicit)
    val casts = subQueryBuilder.castStrategy match {
      case tuple: CastTuple => tuple.getCasts
      case _                => Nil
    }
    (sql, casts)
  }
}
