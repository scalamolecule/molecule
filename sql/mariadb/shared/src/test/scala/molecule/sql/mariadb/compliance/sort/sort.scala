package molecule.sql.mariadb.compliance.sort

import molecule.coreTests.spi.sort._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object SortAggr extends SortAggr with TestAsync_mariadb
object SortBasics extends SortBasics with TestAsync_mariadb
object SortDynamic extends SortDynamic with TestAsync_mariadb
object SortExprOpt extends SortExprOpt with TestAsync_mariadb
object SortNested extends SortNested with TestAsync_mariadb

