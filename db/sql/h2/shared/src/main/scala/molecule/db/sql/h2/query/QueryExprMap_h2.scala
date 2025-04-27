package molecule.db.sql.h2.query

import molecule.core.query.Model2Query
import molecule.db.sql.core.query.{QueryExprMap, SqlQueryBase}

trait QueryExprMap_h2
  extends QueryExprMap
    with LambdasMap_h2 { self: Model2Query & SqlQueryBase =>

}