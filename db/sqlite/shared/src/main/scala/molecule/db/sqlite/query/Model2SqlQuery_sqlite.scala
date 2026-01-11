package molecule.db.sqlite.query

import molecule.core.dataModel.Element
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

  override protected def buildSubQuerySqlWithCasts(subElements: List[Element], subQueryAlias: String, optLimit: Option[Int], optOffset: Option[Int], isImplicit: Boolean): (String, List[Cast]) = {
    val subQueryBuilder = new Model2SqlQuery_sqlite(subElements)
    subQueryBuilder.insideSubQuery = true
    subQueryBuilder.resolveElements(subElements)
    val sql = subQueryBuilder.renderSubQuery(2, Some(subQueryAlias), optLimit, optOffset, isImplicit)
    val casts = subQueryBuilder.castStrategy match {
      case tuple: CastTuple => tuple.getCasts
      case _                => Nil
    }
    (sql, casts)
  }

  override def pagination(
    optLimit: Option[Int], optOffset: Option[Int], isBackwards: Boolean
  ): String = {
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