package molecule.sql.sqlite.query

import molecule.boilerplate.ast.Model._
import molecule.sql.core.query._


class Model2SqlQuery_sqlite[Tpl](elements0: List[Element])
  extends Model2SqlQuery[Tpl](elements0)
    with ResolveExprOne_sqlite
    with ResolveExprSet_sqlite
    with ResolveExprSeq_sqlite
    with ResolveExprMap_sqlite
    with ResolveExprSetRefAttr_sqlite
    with SqlQueryBase{


  override def pagination(
    optLimit: Option[Int], optOffset: Option[Int], isBackwards: Boolean
  ): String = {
    val limit_ = if (isNestedMan || isNestedOpt) {
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

    val offset_ = if (isNestedMan || isNestedOpt) {
      ""
    } else {
      optOffset.fold("")(offset => s"\nOFFSET " + offset.abs)
    }

    s"$limit_$offset_"
  }
}