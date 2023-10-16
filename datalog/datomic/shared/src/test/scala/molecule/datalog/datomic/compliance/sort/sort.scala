package molecule.datalog.datomic.compliance.sort

import molecule.coreTests.spi.sort._
import molecule.datalog.datomic.setup.TestAsync_datomic

object SortAggr extends SortAggr with TestAsync_datomic
object SortBasics extends SortBasics with TestAsync_datomic
object SortDynamic extends SortDynamic with TestAsync_datomic
object SortExprOpt extends SortExprOpt with TestAsync_datomic
object SortNested extends SortNested with TestAsync_datomic
