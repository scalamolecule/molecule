package molecule.sql.h2.query

import molecule.sql.core.query.{QueryExprMap, SqlQueryBase}

trait QueryExprMap_h2
  extends QueryExprMap
    with LambdasMap_h2 { self: SqlQueryBase =>

}