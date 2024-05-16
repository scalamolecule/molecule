package molecule.datalog.datomic.compliance.crud.update.filter

import molecule.coreTests.spi.crud.update.filter._
import molecule.datalog.datomic.setup.TestAsync_datomic

object FilterOne extends FilterOne with TestAsync_datomic
object FilterSet extends FilterSet with TestAsync_datomic
object FilterSeq extends FilterSeq with TestAsync_datomic
object FilterMap extends FilterMap with TestAsync_datomic
