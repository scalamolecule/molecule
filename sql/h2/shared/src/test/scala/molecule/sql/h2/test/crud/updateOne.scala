package molecule.sql.h2.test.crud

import molecule.coreTests.test.crud.update.one._
import molecule.sql.h2.setup.TestAsync_h2

object UpdateOne_id extends UpdateOne_id with TestAsync_h2
object UpdateOne_filter extends UpdateOne_filter with TestAsync_h2
object UpdateOne_uniqueAttr extends UpdateOne_uniqueAttr with TestAsync_h2
