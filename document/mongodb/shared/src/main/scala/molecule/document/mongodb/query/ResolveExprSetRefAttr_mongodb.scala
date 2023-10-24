package molecule.document.mongodb.query

import molecule.document.mongodb.query.{ResolveExprSetRefAttr, SqlQueryBase}

trait ResolveExprSetRefAttr_mongodb
  extends ResolveExprSetRefAttr
    with LambdasSet_mongodb { self: SqlQueryBase =>

}