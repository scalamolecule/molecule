package molecule.sql.h2.query

import molecule.sql.core.query.{QueryExprSetRefAttr, SqlQueryBase}

trait QueryExprSetRefAttr_h2
  extends QueryExprSetRefAttr
    with LambdasSet_h2 { self: SqlQueryBase =>

}