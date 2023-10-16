package molecule.sql.h2.compliance.sort

import molecule.coreTests.spi.sort._
import molecule.sql.h2.setup.TestAsync_h2

object SortAggr extends SortAggr with TestAsync_h2
object SortBasics extends SortBasics with TestAsync_h2
object SortDynamic extends SortDynamic with TestAsync_h2
object SortExprOpt extends SortExprOpt with TestAsync_h2
object SortNested extends SortNested with TestAsync_h2

