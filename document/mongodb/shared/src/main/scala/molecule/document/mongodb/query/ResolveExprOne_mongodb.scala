package molecule.document.mongodb.query

import molecule.document.mongodb.query.{ResolveExprOne, SqlQueryBase}

trait ResolveExprOne_mongodb
  extends ResolveExprOne
    with LambdasOne_mongodb { self: SqlQueryBase =>

}