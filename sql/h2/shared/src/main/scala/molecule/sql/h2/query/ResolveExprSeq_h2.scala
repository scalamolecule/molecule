package molecule.sql.h2.query

import molecule.sql.core.query.{ResolveExprSeq, SqlQueryBase}

trait ResolveExprSeq_h2
  extends ResolveExprSeq
    with LambdasSeq_h2 { self: SqlQueryBase =>

}