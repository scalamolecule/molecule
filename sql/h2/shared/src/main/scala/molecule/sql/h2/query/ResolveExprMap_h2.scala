package molecule.sql.h2.query

import molecule.sql.core.query.{ResolveExprMap, SqlQueryBase}

trait ResolveExprMap_h2
  extends ResolveExprMap
    with LambdasMap_h2 { self: SqlQueryBase =>

}