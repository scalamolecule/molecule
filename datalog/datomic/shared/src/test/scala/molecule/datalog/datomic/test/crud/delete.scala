package molecule.datalog.datomic.test.crud

import molecule.coreTests.test.crud.delete._
import molecule.datalog.datomic.setup.TestAsync_datomic

object Delete_id extends Delete_id with TestAsync_datomic
object Delete_filter extends Delete_filter with TestAsync_datomic
object Delete_uniqueAttr extends Delete_uniqueAttr with TestAsync_datomic
