package molecule.document.mongodb.compliance2.sort

import molecule.coreTests.spi.sort._
import molecule.document.mongodb.setup.TestAsync_mongodb2

object SortAggr extends SortAggr with TestAsync_mongodb2
object SortBasics extends SortBasics with TestAsync_mongodb2
object SortDynamic extends SortDynamic with TestAsync_mongodb2
object SortExprOpt extends SortExprOpt with TestAsync_mongodb2
object SortNested extends SortNested with TestAsync_mongodb2

