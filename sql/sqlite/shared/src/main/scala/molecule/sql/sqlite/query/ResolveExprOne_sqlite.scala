package molecule.sql.sqlite.query

import molecule.sql.core.query.{ResolveExprOne, SqlQueryBase}

trait ResolveExprOne_sqlite
  extends ResolveExprOne
    with LambdasOne_sqlite { self: SqlQueryBase =>

}