package molecule.sql.h2.query

import molecule.sql.core.query.{ResolveExprOne, SqlQueryBase}

trait ResolveExprOne_h2
  extends ResolveExprOne
    with LambdasOne_h2 { self: SqlQueryBase =>

}