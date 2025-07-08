package molecule.db.sql.h2.query

import molecule.db.common.query.{Model2Query, QueryExprSeq, SqlQueryBase}

trait QueryExprSeq_h2
  extends QueryExprSeq
    with LambdasSeq_h2 { self: Model2Query & SqlQueryBase =>

}