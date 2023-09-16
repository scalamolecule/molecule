package molecule.sql.h2.test.crud

import molecule.coreTests.test.crud.update.set._
import molecule.sql.h2.setup.TestAsync_h2

object UpdateSet_id extends UpdateSet_id with TestAsync_h2
object UpdateSet_filter extends UpdateSet_filter with TestAsync_h2
object UpdateSet_uniqueAttr extends UpdateSet_uniqueAttr with TestAsync_h2
