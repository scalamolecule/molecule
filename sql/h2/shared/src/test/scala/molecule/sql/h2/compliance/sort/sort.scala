package molecule.sql.h2.compliance.sort

import molecule.coreTests.spi.sort._
import molecule.sql.h2.setup.Test_h2_async

object Test_SortAggr extends SortAggr with Test_h2_async
object Test_SortBasics extends SortBasics with Test_h2_async
object Test_SortDynamic extends SortDynamic with Test_h2_async
object Test_SortNested extends SortNested with Test_h2_async

