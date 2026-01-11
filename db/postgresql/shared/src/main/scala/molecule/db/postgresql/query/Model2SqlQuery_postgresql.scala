package molecule.db.postgresql.query

import molecule.core.dataModel.Element
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

  override protected def buildSubQuerySqlWithCasts(subElements: List[Element], subQueryAlias: String, optLimit: Option[Int], optOffset: Option[Int], isImplicit: Boolean): (String, List[Cast]) = {
    val subQueryBuilder = new Model2SqlQuery_postgresql(subElements)
    subQueryBuilder.insideSubQuery = true
    subQueryBuilder.resolveElements(subElements)
    if (subQueryBuilder.hasManSubQueryAttr) {
      hasManSubQueryAttr = true
    }
    val sql = subQueryBuilder.renderSubQuery(2, Some(subQueryAlias), optLimit, optOffset, isImplicit)
    val casts = subQueryBuilder.castStrategy match {
      case tuple: CastTuple => tuple.getCasts
      case _                => Nil
    }
    (sql, casts)
  }
}