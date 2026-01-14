package molecule.db.mysql.query

import scala.collection.mutable.ListBuffer
import molecule.core.dataModel.{Attr, Element, Tacit}
import molecule.db.common.query.*
import molecule.db.common.query.casting.strategy.CastTuple

class Model2SqlQuery_mysql(elements0: List[Element])
  extends Model2SqlQuery(elements0)
    with QueryExprOne_mysql
    with QueryExprSet_mysql
    with QueryExprSeq_mysql
    with QueryExprMap_mysql
    with QueryExprSetRefAttr_mysql
    with SqlQueryBase {

  // Override to handle multiple-column SELECT subqueries
  // MySQL doesn't support ROW types in SELECT clause, so generate multiple scalar subqueries
  override protected def querySubQuery(subElements: List[Element], optLimit: Option[Int], optOffset: Option[Int], isJoin: Boolean): Unit = {
    if (!isJoin) {
      // Check if we have multiple non-tacit attributes
      val nonTacitAttrs = subElements.collect { case a: Attr if !a.isInstanceOf[Tacit] => a }

      if (nonTacitAttrs.length > 1) {
        // Generate separate scalar subqueries for each attribute
        val allCasts = scala.collection.mutable.ListBuffer.empty[Cast]

        nonTacitAttrs.foreach { attr =>
          subQueryIndex += 1
          val alias = subQueryAlias

          val wasInsideSubQuery = insideSubQuery
          insideSubQuery = true

          // Build subquery with just this one attribute
          val (subquerySql, subQueryCasts) = buildSubQuerySqlWithCasts(
            List(attr), alias, optLimit, optOffset, isImplicit = false, isJoin = false
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
        return
      }
    }

    // For .join() or single-column .select(), use default behavior
    super.querySubQuery(subElements, optLimit, optOffset, isJoin)
  }

  override protected def buildSubQuerySqlWithCasts(
    subElements: List[Element], subQueryAlias: String, optLimit: Option[Int], optOffset: Option[Int],
    isImplicit: Boolean, isJoin: Boolean
  ): (String, List[Cast]) = {
    val subQueryBuilder = new Model2SqlQuery_mysql(subElements)
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
        // SELECT: MySQL doesn't support ROW types - will use multiple scalar subqueries
        // (handled at querySubQuery level, but if we somehow get here, treat as separate)
        subqueryCasts
      }
    } else {
      subqueryCasts
    }

    (sql, casts)
  }

  override def getWhereClauses: ListBuffer[String] = {
    resolveElements(elements0)
    val clauses    = where.map {
      case (col, expr) => s"$col $expr"
    }
    val joinsExist = Nil

    // This would be a false positive only working for `Entity`...
    //    val joinsExist = if (joins.isEmpty) Nil else
    //      List(
    //        s"""Entity.id IN (
    //           |  SELECT Entity.id FROM (
    //           |    SELECT Entity.id FROM Entity
    //           |      ${mkJoins(2).trim}
    //           |  ) AS t
    //           |)""".stripMargin)

    clauses ++ joinsExist
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
