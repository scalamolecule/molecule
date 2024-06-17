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
    with SqlQueryBase