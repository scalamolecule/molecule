package molecule.db.sql.h2.query

import molecule.db.core.query.Model2Query
import molecule.db.sql.core.query.{QueryExprSet, SqlQueryBase}

trait QueryExprSet_h2
  extends QueryExprSet
    with LambdasSet_h2 { self: Model2Query & SqlQueryBase =>

}