package molecule.datalog.datomic.compliance.crud

import molecule.coreTests.spi.crud.update.array._
import molecule.datalog.datomic.setup.TestAsync_datomic

object UpdateArray_id extends UpdateArray_id with TestAsync_datomic
object UpdateArray_filter extends UpdateArray_filter with TestAsync_datomic
object UpdateArray_uniqueAttr extends UpdateArray_uniqueAttr with TestAsync_datomic
