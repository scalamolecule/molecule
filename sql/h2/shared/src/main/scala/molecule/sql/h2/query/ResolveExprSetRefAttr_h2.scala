package molecule.sql.h2.query

import molecule.sql.core.query.{ResolveExprSetRefAttr, SqlQueryBase}

trait ResolveExprSetRefAttr_h2
  extends ResolveExprSetRefAttr
    with LambdasSet_h2 { self: SqlQueryBase =>

}