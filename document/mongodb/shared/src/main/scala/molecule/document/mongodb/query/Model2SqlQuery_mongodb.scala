package molecule.document.mongodb.query

import molecule.boilerplate.ast.Model._
import molecule.document.mongodb.query._


class Model2SqlQuery_mongodb[Tpl](elements0: List[Element])
  extends Model2SqlQuery[Tpl](elements0)
    with ResolveExprOne_mongodb
    with ResolveExprSet_mongodb
    with ResolveExprSetRefAttr_mongodb
    with SqlQueryBase {
}