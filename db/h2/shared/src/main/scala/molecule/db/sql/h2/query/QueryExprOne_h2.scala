package molecule.db.sql.h2.query

import molecule.db.common.query.{Model2Query, QueryExprOne, SqlQueryBase}

trait QueryExprOne_h2
  extends QueryExprOne
    with LambdasOne_h2 { self: Model2Query & SqlQueryBase =>

}