package molecule.db.mariadb.query

import molecule.core.dataModel.*
import molecule.db.common.query.*

class Model2SqlQuery_mariadb(elements0: List[Element])
  extends Model2SqlQuery(elements0)
    with QueryExprOne_mariadb
    with QueryExprSet_mariadb
    with QueryExprSeq_mariadb
    with QueryExprMap_mariadb
    with QueryExprSetRefAttr_mariadb
    with QueryExprSubQuery_mariadb
    with Lambdas_mariadb // obs: has to be last to override resolvers above
    with SqlQueryBase {

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
