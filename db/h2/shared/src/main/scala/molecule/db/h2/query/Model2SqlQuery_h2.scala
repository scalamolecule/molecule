package molecule.db.h2.query

import molecule.core.dataModel.*
import molecule.db.common.query.*
import molecule.db.common.query.casting.strategy.CastTuple

class Model2SqlQuery_h2(elements0: List[Element])
  extends Model2SqlQuery(elements0)
    with QueryExprOne_h2
    with QueryExprSet_h2
    with QueryExprSeq_h2
    with QueryExprMap_h2
    with QueryExprSetRefAttr_h2
    with SqlQueryBase {

  // Override to split multi-column SELECT subqueries when aggregating IDs
  // This allows aggregating IDs across relationships which doesn't work with ROW types
  override protected def querySubQuery(subElements: List[Element], optLimit: Option[Int], optOffset: Option[Int], isJoin: Boolean): Unit = {
    if (!isJoin) {
      val nonTacitAttrs = subElements.collect { case a: Attr if !isTacit(a) => a }

      // Check if we're aggregating on any ID attributes
      val hasIdAggregation = nonTacitAttrs.exists { attr =>
        attr.name == "id" && attr.op.isInstanceOf[AggrFn]
//        attr.name == "id" && attr.op.exists(_.startsWith("aggr"))
      }

      // Check if any attribute has sorting
      val hasSorting = nonTacitAttrs.exists(_.sort.isDefined)

      if (nonTacitAttrs.length > 1 && (hasIdAggregation || hasSorting)) {
        // Split into separate scalar subqueries when aggregating IDs
        val allCasts = scala.collection.mutable.ListBuffer.empty[Cast]
        val otherElements = subElements.filter {
          case a: Attr if isTacit(a) => true  // Tacit attrs for WHERE clauses
          case _: Ref => true                  // Ref elements for JOIN relationships
          case _ => false
        }

        nonTacitAttrs.foreach { attr =>
          subQueryIndex += 1
          val alias = subQueryAlias

          val wasInsideSubQuery = insideSubQuery
          insideSubQuery = true

          // Build subquery with this attribute + all tacit/ref elements
          val subqueryElements = otherElements :+ attr
          val (subquerySql, subQueryCasts) = buildSubQuerySqlWithCasts(
            subqueryElements, alias, optLimit, optOffset, isImplicit = false, isJoin = false
          )

          select += subquerySql
          allCasts ++= subQueryCasts

          // Propagate sorting if this attribute has sorting
          attr.sort.foreach { sort =>
            val (dir, arity) = (sort.head, sort.substring(1, 2).toInt)
            val sortDir = if (dir == 'a') "" else " DESC"
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

    // For .join() or single-column .select(), or no ID aggregation, use default behavior
    super.querySubQuery(subElements, optLimit, optOffset, isJoin)
  }

  private def isTacit(attr: Attr): Boolean = attr match {
    case _: Tacit => true
    case _        => false
  }

  // Cast multiple columns into a tuple (used when splitting subqueries)
  private def castMultipleColumns(columnCasts: List[Cast]): Cast = {
    val n = columnCasts.length
    (row: RS, baseIndex: Int) => {
      val values = columnCasts.zipWithIndex.map { case (cast, i) =>
        cast(row, baseIndex + i)
      }
      n match {
        case 2  => (values(0), values(1))
        case 3  => (values(0), values(1), values(2))
        case 4  => (values(0), values(1), values(2), values(3))
        case 5  => (values(0), values(1), values(2), values(3), values(4))
        case 6  => (values(0), values(1), values(2), values(3), values(4), values(5))
        case 7  => (values(0), values(1), values(2), values(3), values(4), values(5), values(6))
        case 8  => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7))
        case 9  => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8))
        case 10 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9))
        case 11 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10))
        case 12 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11))
        case 13 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12))
        case 14 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13))
        case 15 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13), values(14))
        case 16 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13), values(14), values(15))
        case 17 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13), values(14), values(15), values(16))
        case 18 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13), values(14), values(15), values(16), values(17))
        case 19 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13), values(14), values(15), values(16), values(17), values(18))
        case 20 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13), values(14), values(15), values(16), values(17), values(18), values(19))
        case 21 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13), values(14), values(15), values(16), values(17), values(18), values(19), values(20))
        case 22 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13), values(14), values(15), values(16), values(17), values(18), values(19), values(20), values(21))
        case _  => throw new IllegalArgumentException(s"Unsupported tuple arity: $n")
      }
    }
  }

  override protected def buildSubQuerySqlWithCasts(subElements: List[Element], subQueryAlias: String, optLimit: Option[Int], optOffset: Option[Int], isImplicit: Boolean, isJoin: Boolean): (String, List[Cast]) = {
    // Create a new query builder instance for the subquery
    val subQueryBuilder = new Model2SqlQuery_h2(subElements)

    // Mark that we're inside a subquery (to allow ID filter attributes)
    subQueryBuilder.insideSubQuery = true

    // Mark if this is a JOIN subquery (affects correlation handling)
    subQueryBuilder.insideJoinSubQuery = isJoin

    // Resolve the subquery elements
    subQueryBuilder.resolveElements(subElements)

    // Propagate the flag back to main query if subquery has mandatory attributes
    if (subQueryBuilder.hasManSubQueryAttr) {
      hasManSubQueryAttr = true
    }

    // For SELECT clause subqueries (correlated, scalar), clear ORDER BY from subquery
    // UNLESS there's a LIMIT/OFFSET (ORDER BY determines which rows are selected)
    // The ordering will be applied to the outer query instead
    if (!isJoin && optLimit.isEmpty && optOffset.isEmpty) {
      subQueryBuilder.orderBy.clear()
    }

    // Get the SQL and casts
    val sql = subQueryBuilder.renderSubQuery(2, Some(subQueryAlias), optLimit, optOffset, isImplicit)
    val subqueryCasts = subQueryBuilder.castStrategy match {
      case tuple: CastTuple => tuple.getCasts
      case _                => Nil
    }

    // Handle casts differently for JOIN vs SELECT subqueries
    val casts = if (subqueryCasts.length > 1) {
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

    (sql, casts)
  }

  // Cast function to handle multi-value SELECT subquery ROWs (H2 returns ROW type)
  // This will be overridden in the JVM-specific implementation
  protected def castSubqueryRow(rowCasts: List[Cast]): Cast = {
    throw new UnsupportedOperationException(
      "Subquery with multiple columns not yet supported on this platform. " +
      "Override castSubqueryRow in platform-specific implementation."
    )
  }

  // Cast function to handle multi-column JOIN subqueries (columns are separate)
  // Groups individual column values into a tuple
  // This will be overridden in the JVM-specific implementation
  protected def castJoinSubqueryTuple(columnCasts: List[Cast]): Cast = {
    throw new UnsupportedOperationException(
      "JOIN subquery with multiple columns not yet supported on this platform. " +
      "Override castJoinSubqueryTuple in platform-specific implementation."
    )
  }

  // Default implementation for platforms that don't support ROW types yet
  private def castSubqueryRowDefault(rowCasts: List[Cast]): Cast = {
    (row: RS, paramIndex: Int) => {
      val values = rowCasts.zipWithIndex.map { case (cast, idx) =>
        cast(row, paramIndex + idx)
      }
      // Return as tuple based on arity
      values.length match {
        case 2  => (values(0), values(1))
        case 3  => (values(0), values(1), values(2))
        case 4  => (values(0), values(1), values(2), values(3))
        case 5  => (values(0), values(1), values(2), values(3), values(4))
        case 6  => (values(0), values(1), values(2), values(3), values(4), values(5))
        case 7  => (values(0), values(1), values(2), values(3), values(4), values(5), values(6))
        case 8  => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7))
        case 9  => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8))
        case 10 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9))
        case 11 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10))
        case 12 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11))
        case 13 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12))
        case 14 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13))
        case 15 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13), values(14))
        case 16 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13), values(14), values(15))
        case 17 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13), values(14), values(15), values(16))
        case 18 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13), values(14), values(15), values(16), values(17))
        case 19 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13), values(14), values(15), values(16), values(17), values(18))
        case 20 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13), values(14), values(15), values(16), values(17), values(18), values(19))
        case 21 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13), values(14), values(15), values(16), values(17), values(18), values(19), values(20))
        case 22 => (values(0), values(1), values(2), values(3), values(4), values(5), values(6), values(7), values(8), values(9), values(10), values(11), values(12), values(13), values(14), values(15), values(16), values(17), values(18), values(19), values(20), values(21))
        case _  => throw new IllegalArgumentException(s"Unsupported tuple arity: ${values.length}")
      }
    }
  }
}