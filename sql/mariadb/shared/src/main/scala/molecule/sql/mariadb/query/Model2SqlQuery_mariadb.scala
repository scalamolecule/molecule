package molecule.sql.mariadb.query

import molecule.boilerplate.ast.Model._
import molecule.sql.core.query._


class Model2SqlQuery_mariadb[Tpl](elements0: List[Element])
  extends Model2SqlQuery[Tpl](elements0)
    with QueryExprOne_mariadb
    with QueryExprSet_mariadb
    with QueryExprSeq_mariadb
    with QueryExprMap_mariadb
    with QueryExprSetRefAttr_mariadb
    with Lambdas_mariadb // obs: has to be last to override resolvers above
    with SqlQueryBase {


  override def pagination(optLimit: Option[Int], optOffset: Option[Int], isBackwards: Boolean): String = {
    if (isManNested || isOptNested) {
      ""
    } else if (hardLimit == 0) {
      if (!isBackwards) {
        (optOffset, optLimit) match {
          case (None, None)                => ""
          case (None, Some(limit))         => s"\nLIMIT 0, $limit"
          case (Some(offset), None)        => s"\nLIMIT $offset, 18446744073709551615"
          case (Some(offset), Some(limit)) =>
            s"\nLIMIT $offset, $limit"
        }
      } else {
        (optOffset, optLimit) match {
          case (None, None)                => ""
          case (None, Some(limit))         => s"\nLIMIT 0, ${-limit}"
          case (Some(offset), None)        => s"\nLIMIT ${-offset}, 18446744073709551615"
          case (Some(offset), Some(limit)) => s"\nLIMIT ${-offset}, ${-limit}"
        }
      }
    } else {
      optOffset match {
        case None                        => s"\nLIMIT 0, $hardLimit"
        case Some(offset) if isBackwards => s"\nLIMIT ${-offset}, $hardLimit"
        case Some(offset)                => s"\nLIMIT $offset, $hardLimit"
      }
    }
  }
}