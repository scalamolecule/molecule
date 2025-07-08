package molecule.db.sql.h2.query

import molecule.db.common.query.{Model2Query, QueryExprSetRefAttr, SqlQueryBase}

trait QueryExprSetRefAttr_h2
  extends QueryExprSetRefAttr
    with LambdasSet_h2 { self: Model2Query & SqlQueryBase =>

}