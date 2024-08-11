package molecule.datalog.datomic.compliance.sort

import molecule.coreTests.spi.sort._
import molecule.datalog.datomic.setup.TestAsync_datomic

object Test_SortAggr extends SortAggr with TestAsync_datomic
object Test_SortBasics extends SortBasics with TestAsync_datomic
object Test_SortDynamic extends SortDynamic with TestAsync_datomic
object Test_SortNested extends SortNested with TestAsync_datomic
