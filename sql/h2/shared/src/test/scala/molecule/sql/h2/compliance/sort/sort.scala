package molecule.sql.h2.compliance.sort

import molecule.coreTests.spi.sort._
import molecule.sql.h2.setup.TestAsync_h2

object Test_SortAggr extends SortAggr with TestAsync_h2
object Test_SortBasics extends SortBasics with TestAsync_h2
object Test_SortDynamic extends SortDynamic with TestAsync_h2
object Test_SortNested extends SortNested with TestAsync_h2

