package molecule.sql.postgres.query

import molecule.boilerplate.ast.Model._
import molecule.sql.core.query._


class Model2SqlQuery_postgres[Tpl](elements0: List[Element])
  extends Model2SqlQuery[Tpl](elements0)
    with QueryExprOne_postgres
    with QueryExprSet_postgres
    with QueryExprSeq_postgres
    with QueryExprMap_postgres
    with QueryExprSetRefAttr_postgres
    with SqlQueryBase