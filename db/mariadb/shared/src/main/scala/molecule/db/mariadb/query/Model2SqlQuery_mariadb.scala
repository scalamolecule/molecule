package molecule.db.mariadb.query

import molecule.core.dataModel.*
import molecule.db.common.query.*
import molecule.db.common.query.casting.strategy.CastTuple

class Model2SqlQuery_mariadb(elements0: List[Element])
  extends Model2SqlQuery(elements0)
    with QueryExprOne_mariadb
    with QueryExprSet_mariadb
    with QueryExprSeq_mariadb
    with QueryExprMap_mariadb
    with QueryExprSetRefAttr_mariadb
    with Lambdas_mariadb // obs: has to be last to override resolvers above
    with SqlQueryBase {

  // Override to handle multiple-column SELECT subqueries
  // MariaDB doesn't support ROW types in SELECT clause, so generate multiple scalar subqueries
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
      nonTacitAttrs.length > 1
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
      // This preserves WHERE clauses (tacit) and JOINs (refs)
      val subqueryElements             = otherElements :+ attr
      val (subquerySql, subQueryCasts) = buildSubQuerySqlWithCasts(
        subqueryElements, alias, optLimit, optOffset, isImplicit = false, isJoin = false
      )

      // Add as separate SELECT clause subquery
      select += subquerySql
      allCasts ++= subQueryCasts

      // Propagate sorting if needed (only for first attribute in multi-column subquery)
      if (attr == nonTacitAttrs.head) {
        attr.sort.foreach { sort =>
          val (dir, arity)   = (sort.head, sort.substring(1, 2).toInt)
          val sortDir        = if (dir == 'a') "" else " DESC"
          val selectPosition = select.length.toString
          orderBy += ((level, arity, selectPosition, sortDir))
        }
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
    val subQueryBuilder = new Model2SqlQuery_mariadb(subElements)
    subQueryBuilder.insideSubQuery = true
    subQueryBuilder.insideJoinSubQuery = isJoin
    subQueryBuilder.resolveElements(subElements)
    if (subQueryBuilder.hasManSubQueryAttr) {
      hasManSubQueryAttr = true
    }

    // For SELECT clause correlated subqueries with aggregates, turn off DISTINCT
    // Keep DISTINCT for implicit subqueries (comparisons) and JOIN subqueries
    if (!isJoin && !isImplicit && subQueryBuilder.aggregate) {
      subQueryBuilder.distinct = false
    }
    // For SELECT clause subqueries (correlated, scalar), clear ORDER BY from subquery
    // UNLESS there's a LIMIT/OFFSET (ORDER BY determines which rows are selected)
    // The ordering will be applied to the outer query instead
    if (!isJoin && optLimit.isEmpty && optOffset.isEmpty) {
      subQueryBuilder.orderBy.clear()
    }
    val sql           = subQueryBuilder.renderSubQuery(2, Some(subQueryAlias), optLimit, optOffset, isImplicit)
    val subqueryCasts = subQueryBuilder.castStrategy match {
      case tuple: CastTuple => tuple.getCasts
      case _                => Nil
    }

    // Handle multi-column subqueries differently based on type
    val casts = if (subqueryCasts.length > 1) {
      if (isJoin) {
        // JOIN: columns are separate in result set, group into tuple
        List(castMultipleColumns(subqueryCasts))
      } else {
        // SELECT: MariaDB doesn't support ROW types - will use multiple scalar subqueries
        // (handled at querySubQuery level, but if we somehow get here, treat as separate)
        subqueryCasts
      }
    } else {
      subqueryCasts
    }

    (sql, casts)
  }

  override def pagination(optLimit: Option[Int], optOffset: Option[Int]): String = {
    if (!insideSubQuery && (isManNested || isOptNested)) {
      ""
    } else if (hardLimit == 0) {
      (optOffset, optLimit) match {
        case (None, None)                => ""
        case (None, Some(limit))         => s"\nLIMIT 0, $limit"
        case (Some(offset), None)        => s"\nLIMIT $offset, 18446744073709551615"
        case (Some(offset), Some(limit)) =>
          s"\nLIMIT $offset, $limit"
      }
    } else {
      optOffset match {
        case None         => s"\nLIMIT 0, $hardLimit"
        case Some(offset) => s"\nLIMIT $offset, $hardLimit"
      }
    }
  }
}
