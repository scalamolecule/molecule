package molecule.db.h2.query

import molecule.core.dataModel.Element
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

  override protected def buildSubQuerySqlWithCasts(subElements: List[Element]): (String, List[Cast]) = {
    // Create a new query builder instance for the subquery
    val subQueryBuilder = new Model2SqlQuery_h2(subElements)

    // Mark that we're inside a subquery (to allow ID filter attributes)
    subQueryBuilder.insideSubQuery = true

    // Resolve the subquery elements
    subQueryBuilder.resolveElements(subElements)

    // Get the SQL and casts
    val sql = subQueryBuilder.renderSubQuery(baseIndent = 2)
    val subqueryCasts = subQueryBuilder.castStrategy match {
      case tuple: CastTuple => tuple.getCasts
      case _                => Nil
    }

    // If subquery has multiple values, H2 returns them as a ROW type
    // Wrap casts in ROW handler to extract values from the ROW
    val casts = if (subqueryCasts.length > 1) {
      List(castSubqueryRow(subqueryCasts))
    } else {
      subqueryCasts
    }

    (sql, casts)
  }

  // Cast function to handle multi-value subquery ROWs
  // This will be overridden in the JVM-specific implementation
  protected def castSubqueryRow(rowCasts: List[Cast]): Cast = {
    throw new UnsupportedOperationException(
      "Subquery with multiple columns not yet supported on this platform. " +
      "Override castSubqueryRow in platform-specific implementation."
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