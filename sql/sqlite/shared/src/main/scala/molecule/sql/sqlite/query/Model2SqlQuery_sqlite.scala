package molecule.sql.sqlite.query

import molecule.core.ast.DataModel.Element
import molecule.sql.core.query._

class Model2SqlQuery_sqlite(elements0: List[Element])
  extends Model2SqlQuery(elements0)
    with QueryExprOne_sqlite
    with QueryExprSet_sqlite
    with QueryExprSeq_sqlite
    with QueryExprMap_sqlite
    with QueryExprSetRefAttr_sqlite
    with SqlQueryBase {


  override def pagination(
    optLimit: Option[Int], optOffset: Option[Int], isBackwards: Boolean
  ): String = {
    val limit_ = if (isManNested || isOptNested) {
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

    val offset_ = if (isManNested || isOptNested) {
      ""
    } else {
      optOffset.fold("")(offset => s"\nOFFSET " + offset.abs)
    }

    s"$limit_$offset_"
  }
}