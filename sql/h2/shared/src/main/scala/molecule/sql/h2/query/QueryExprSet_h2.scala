package molecule.sql.h2.query

import molecule.sql.core.query.{QueryExprSet, SqlQueryBase}

trait QueryExprSet_h2
  extends QueryExprSet
    with LambdasSet_h2 { self: SqlQueryBase =>

}