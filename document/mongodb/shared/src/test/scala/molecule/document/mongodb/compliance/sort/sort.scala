package molecule.document.mongodb.compliance.sort

import molecule.coreTests.spi.sort._
import molecule.document.mongodb.setup.TestAsync_mongodb

object SortAggr extends SortAggr with TestAsync_mongodb
object SortBasics extends SortBasics with TestAsync_mongodb
object SortDynamic extends SortDynamic with TestAsync_mongodb
object SortExprOpt extends SortExprOpt with TestAsync_mongodb
object SortNested extends SortNested with TestAsync_mongodb

