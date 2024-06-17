package molecule.sql.sqlite.transaction

import molecule.core.transaction.ResolveSave
import molecule.sql.core.transaction.SqlSave

trait Save_sqlite extends SqlSave { self: ResolveSave =>

  doPrint = false
}