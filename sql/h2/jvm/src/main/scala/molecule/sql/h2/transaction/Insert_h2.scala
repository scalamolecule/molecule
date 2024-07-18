package molecule.sql.h2.transaction

import molecule.core.transaction.{InsertResolvers_, ResolveInsert}
import molecule.sql.core.transaction.SqlInsert

trait Insert_h2 extends SqlInsert { self: ResolveInsert with InsertResolvers_ =>

}