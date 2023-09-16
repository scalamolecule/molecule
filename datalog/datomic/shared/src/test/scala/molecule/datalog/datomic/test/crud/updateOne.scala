package molecule.datalog.datomic.test.crud

import molecule.coreTests.test.crud.update.one._
import molecule.datalog.datomic.setup.TestAsync_datomic

object UpdateOne_id extends UpdateOne_id with TestAsync_datomic
object UpdateOne_filter extends UpdateOne_filter with TestAsync_datomic
object UpdateOne_uniqueAttr extends UpdateOne_uniqueAttr with TestAsync_datomic
