package molecule.datalog.datomic.test.sort

import molecule.coreTests.test.sort._
import molecule.datalog.datomic.setup.TestAsync_datomic

object SortAggr extends SortAggr with TestAsync_datomic
object SortBasics extends SortBasics with TestAsync_datomic
object SortDynamic extends SortDynamic with TestAsync_datomic
object SortExprOpt extends SortExprOpt with TestAsync_datomic
