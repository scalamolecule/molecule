package molecule.sql.h2.query

import molecule.core.query.Model2Query
import molecule.sql.core.query.{QueryExprSeq, SqlQueryBase}

trait QueryExprSeq_h2
  extends QueryExprSeq
    with LambdasSeq_h2 { self: Model2Query & SqlQueryBase =>

}