package molecule.db.sqlite.query

import molecule.core.dataModel.*
import molecule.db.common.query.*

class Model2SqlQuery_sqlite(elements0: List[Element])
  extends Model2SqlQuery(elements0)
    with QueryExprOne_sqlite
    with QueryExprSet_sqlite
    with QueryExprSeq_sqlite
    with QueryExprMap_sqlite
    with QueryExprSetRefAttr_sqlite
    with QueryExprSubQuery_sqlite
    with SqlQueryBase {

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
