package molecule.sql.h2.test.sort

import molecule.coreTests.test.sort._
import molecule.sql.h2.setup.TestAsync_h2

object SortAggr extends SortAggr with TestAsync_h2
object SortBasics extends SortBasics with TestAsync_h2
object SortDynamic extends SortDynamic with TestAsync_h2
object SortExprOpt extends SortExprOpt with TestAsync_h2
object SortNested extends SortNested with TestAsync_h2

