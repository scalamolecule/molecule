package molecule.db.sqlite.query

import molecule.core.dataModel.*
import molecule.db.common.query.*
import molecule.db.common.query.casting.strategy.CastTuple

class Model2SqlQuery_sqlite(elements0: List[Element])
  extends Model2SqlQuery(elements0)
    with QueryExprOne_sqlite
    with QueryExprSet_sqlite
    with QueryExprSeq_sqlite
    with QueryExprMap_sqlite
    with QueryExprSetRefAttr_sqlite
    with SqlQueryBase {

  // Override to handle multiple-column SELECT subqueries
  // SQLite doesn't support ROW types, so generate multiple scalar subqueries
  override protected def querySubQuery(subElements: List[Element], optLimit: Option[Int], optOffset: Option[Int], isJoin: Boolean): Unit = {
    if (!isJoin) {
      // Check if we have multiple non-tacit attributes
      val nonTacitAttrs = subElements.collect { case a: Attr if !a.isInstanceOf[Tacit] => a }

      if (nonTacitAttrs.length > 1) {
        // Generate separate scalar subqueries for each attribute
        // BUT keep all tacit attributes (WHERE correlation) and Ref elements (JOIN relationships)
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
    val subQueryBuilder = new Model2SqlQuery_sqlite(subElements)
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
        // SELECT: SQLite doesn't support ROW types - will use multiple scalar subqueries
        // (handled at querySubQuery level, but if we somehow get here, treat as separate)
        subqueryCasts
      }
    } else {
      subqueryCasts
    }

    (sql, casts)
  }

  override def pagination(optLimit: Option[Int], optOffset: Option[Int]): String = {
    val limit_ = if (!insideSubQuery && (isManNested || isOptNested)) {
      ""
    } else if (hardLimit != 0) {
      s"\nLIMIT $hardLimit"
    } else if (optOffset.isDefined) {
      optLimit.fold {
        "\nLIMIT -1" // SQlite needs limit to allow offset
      }(limit => s"\nLIMIT " + limit.abs)
    } else {
      optLimit.fold("")(limit => s"\nLIMIT " + limit.abs)
    }

    val offset_ = if (!insideSubQuery && (isManNested || isOptNested)) {
      ""
    } else {
      optOffset.fold("")(offset => s"\nOFFSET " + offset.abs)
    }

    s"$limit_$offset_"
  }
}