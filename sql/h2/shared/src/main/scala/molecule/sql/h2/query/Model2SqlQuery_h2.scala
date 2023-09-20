package molecule.sql.h2.query

import molecule.boilerplate.ast.Model._
import molecule.sql.core.query._


class Model2SqlQuery_h2[Tpl](elements0: List[Element])
  extends Model2SqlQuery[Tpl](elements0)
    with ResolveExprOne_h2
    with ResolveExprSet_h2
    with ResolveExprSetRefAttr_h2
    with SqlQueryBase {
}