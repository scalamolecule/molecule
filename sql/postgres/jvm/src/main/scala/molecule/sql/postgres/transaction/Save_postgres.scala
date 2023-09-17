package molecule.sql.postgres.transaction

import molecule.core.transaction.ResolveSave
import molecule.sql.h2.transaction.Save_h2

trait Save_postgres extends Save_h2 { self: ResolveSave =>


}