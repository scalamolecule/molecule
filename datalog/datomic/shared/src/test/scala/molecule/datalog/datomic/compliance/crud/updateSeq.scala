package molecule.datalog.datomic.compliance.crud

import molecule.coreTests.spi.crud.update.seq._
import molecule.datalog.datomic.setup.TestAsync_datomic

object UpdateSeq_id extends UpdateSeq_id with TestAsync_datomic
object UpdateSeq_filter extends UpdateSeq_filter with TestAsync_datomic
object UpdateSeq_uniqueAttr extends UpdateSeq_uniqueAttr with TestAsync_datomic
