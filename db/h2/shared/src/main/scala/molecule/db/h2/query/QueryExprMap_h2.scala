package molecule.db.h2.query

import molecule.db.common.query.{Model2Query, QueryExprMap, SqlQueryBase}

trait QueryExprMap_h2
  extends QueryExprMap
    with LambdasMap_h2 { self: Model2Query & SqlQueryBase =>

}