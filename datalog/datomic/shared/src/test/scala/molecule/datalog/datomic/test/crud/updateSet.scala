package molecule.datalog.datomic.test.crud

import molecule.coreTests.test.crud.update.set._
import molecule.datalog.datomic.setup.TestAsync_datomic

object UpdateSet_id extends UpdateSet_id with TestAsync_datomic
object UpdateSet_filter extends UpdateSet_filter with TestAsync_datomic
object UpdateSet_uniqueAttr extends UpdateSet_uniqueAttr with TestAsync_datomic
