package molecule.document.mongodb.transaction

import molecule.core.transaction.{InsertResolvers_, ResolveInsert}
import molecule.document.mongodb.transaction.SqlInsert

trait Insert_mongodb extends SqlInsert { self: ResolveInsert with InsertResolvers_ =>

  doPrint = false
}