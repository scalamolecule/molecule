package molecule.sql.h2.test.crud

import molecule.coreTests.test.crud.delete._
import molecule.sql.h2.setup.TestAsync_h2

object Delete_id extends Delete_id with TestAsync_h2
object Delete_filter extends Delete_filter with TestAsync_h2
object Delete_uniqueAttr extends Delete_uniqueAttr with TestAsync_h2