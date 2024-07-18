package molecule.sql.h2.transaction

import molecule.core.transaction.ResolveSave
import molecule.sql.core.transaction.SqlSave

trait Save_h2 extends SqlSave { self: ResolveSave =>

}