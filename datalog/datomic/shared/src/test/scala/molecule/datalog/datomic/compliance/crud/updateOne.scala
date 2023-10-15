package molecule.datalog.datomic.compliance.crud

import molecule.coreTests.compliance.crud.update.one._
import molecule.datalog.datomic.setup.TestAsync_datomic

object UpdateOne_id extends UpdateOne_id with TestAsync_datomic
object UpdateOne_filter extends UpdateOne_filter with TestAsync_datomic
object UpdateOne_uniqueAttr extends UpdateOne_uniqueAttr with TestAsync_datomic
