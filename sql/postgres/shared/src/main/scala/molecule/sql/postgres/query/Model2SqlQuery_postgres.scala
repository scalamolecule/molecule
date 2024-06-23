package molecule.sql.postgres.query

import molecule.boilerplate.ast.Model._
import molecule.sql.core.query._


class Model2SqlQuery_postgres[Tpl](elements0: List[Element])
  extends Model2SqlQuery[Tpl](elements0)
    with ResolveExprOne_postgres
    with ResolveExprSet_postgres
    with ResolveExprSeq_postgres
    with ResolveExprMap_postgres
    with ResolveExprSetRefAttr_postgres
    with SqlQueryBase