package molecule.sql.sqlite.query

import molecule.sql.core.query.{ResolveExprSetRefAttr, SqlQueryBase}

trait ResolveExprSetRefAttr_sqlite
  extends ResolveExprSetRefAttr
    with LambdasSet_sqlite { self: SqlQueryBase =>

}