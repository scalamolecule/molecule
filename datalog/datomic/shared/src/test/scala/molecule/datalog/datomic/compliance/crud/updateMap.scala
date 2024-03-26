package molecule.datalog.datomic.compliance.crud

import molecule.coreTests.spi.crud.update.map._
import molecule.datalog.datomic.setup.TestAsync_datomic

object UpdateMap_id extends UpdateMap_id with TestAsync_datomic
object UpdateMap_filter extends UpdateMap_filter with TestAsync_datomic
object UpdateMap_uniqueAttr extends UpdateMap_uniqueAttr with TestAsync_datomic
