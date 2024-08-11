package molecule.datalog.datomic.compliance.crud.update.filter

import molecule.coreTests.spi.crud.update.filter._
import molecule.datalog.datomic.setup.TestAsync_datomic

object Test_FilterOne extends FilterOne with TestAsync_datomic
object Test_FilterSet extends FilterSet with TestAsync_datomic
object Test_FilterSeq extends FilterSeq with TestAsync_datomic
object Test_FilterMap extends FilterMap with TestAsync_datomic
