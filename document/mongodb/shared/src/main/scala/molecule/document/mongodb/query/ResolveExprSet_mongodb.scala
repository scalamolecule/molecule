package molecule.document.mongodb.query

import molecule.document.mongodb.query.{ResolveExprSet, SqlQueryBase}

trait ResolveExprSet_mongodb
  extends ResolveExprSet
    with LambdasSet_mongodb { self: SqlQueryBase =>

}