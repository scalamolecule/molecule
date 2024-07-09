package molecule.sql.h2.query

import molecule.core.query.Model2Query
import molecule.sql.core.query.{QueryExprOne, SqlQueryBase}

trait QueryExprOne_h2
  extends QueryExprOne
    with LambdasOne_h2 { self: Model2Query with SqlQueryBase =>

}