package molecule.sql.postgres.query

import molecule.boilerplate.ast.Model._
import molecule.sql.core.query._


class Model2SqlQuery_postgres[Tpl](elements0: List[Element])
  extends Model2SqlQuery[Tpl](elements0)
    with ResolveExprOne_postgres[Tpl]
    with ResolveExprSet_postgres[Tpl]
    with ResolveExprSetRefAttr_postgres[Tpl]
    with SqlQueryBase {

}