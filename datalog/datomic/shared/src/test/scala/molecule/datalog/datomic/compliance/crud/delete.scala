package molecule.datalog.datomic.compliance.crud

import molecule.coreTests.spi.crud.delete._
import molecule.datalog.datomic.setup.TestAsync_datomic

object Delete_id extends Delete_id with TestAsync_datomic
object Delete_filter extends Delete_filter with TestAsync_datomic
object Delete_uniqueAttr extends Delete_uniqueAttr with TestAsync_datomic