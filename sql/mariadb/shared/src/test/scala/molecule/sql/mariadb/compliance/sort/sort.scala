package molecule.sql.mariadb.compliance.sort

import molecule.coreTests.spi.sort._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object Test_SortAggr extends SortAggr with TestAsync_mariadb
object Test_SortBasics extends SortBasics with TestAsync_mariadb
object Test_SortDynamic extends SortDynamic with TestAsync_mariadb
object Test_SortNested extends SortNested with TestAsync_mariadb

