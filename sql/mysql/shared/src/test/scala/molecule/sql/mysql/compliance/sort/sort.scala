package molecule.sql.mysql.compliance.sort

import molecule.coreTests.spi.sort._
import molecule.sql.mysql.setup.TestAsync_mysql

object Test_SortAggr extends SortAggr with TestAsync_mysql
object Test_SortBasics extends SortBasics with TestAsync_mysql
object Test_SortDynamic extends SortDynamic with TestAsync_mysql
object Test_SortNested extends SortNested with TestAsync_mysql

