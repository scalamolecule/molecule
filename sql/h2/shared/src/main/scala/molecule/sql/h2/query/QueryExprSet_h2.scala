package molecule.sql.h2.query

import molecule.core.query.Model2Query
import molecule.sql.core.query.{QueryExprSet, SqlQueryBase}

trait QueryExprSet_h2
  extends QueryExprSet
    with LambdasSet_h2 { self: Model2Query with SqlQueryBase =>

}