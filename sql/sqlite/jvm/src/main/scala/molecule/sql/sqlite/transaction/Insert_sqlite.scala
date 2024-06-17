package molecule.sql.sqlite.transaction

import molecule.core.transaction.{InsertResolvers_, ResolveInsert}
import molecule.sql.core.transaction.SqlInsert

trait Insert_sqlite extends SqlInsert { self: ResolveInsert with InsertResolvers_ =>

  doPrint = false
  //    doPrint = true

}