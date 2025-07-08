package molecule.db.sql.h2.query

import molecule.db.common.query.{Model2Query, QueryExprSet, SqlQueryBase}

trait QueryExprSet_h2
  extends QueryExprSet
    with LambdasSet_h2 { self: Model2Query & SqlQueryBase =>

}