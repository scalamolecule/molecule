package molecule.sql.h2.query

import molecule.sql.core.query.{ResolveExprSet, SqlQueryBase}

trait ResolveExprSet_h2
  extends ResolveExprSet
    with LambdasSet_h2 { self: SqlQueryBase =>

}