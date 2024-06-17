package molecule.sql.sqlite.query

import molecule.sql.core.query.{ResolveExprSeq, SqlQueryBase}

trait ResolveExprSeq_sqlite
  extends ResolveExprSeq
    with LambdasSeq_sqlite { self: SqlQueryBase =>

}