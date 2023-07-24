package molecule.sql.jdbc.test.crud

import molecule.coreTests.test.crud.update.set._
import molecule.sql.jdbc.setup.CoreTestAsync

object UpdateSet_id$ extends UpdateSet_id with CoreTestAsync
object UpdateSet_filter extends UpdateSet_filter with CoreTestAsync
object UpdateSet_uniqueAttr extends UpdateSet_uniqueAttr with CoreTestAsync
