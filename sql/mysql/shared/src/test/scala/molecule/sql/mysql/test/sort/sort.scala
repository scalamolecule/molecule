package molecule.sql.mysql.test.sort

import molecule.coreTests.test.sort._
import molecule.sql.mysql.setup.TestAsync_mysql

object SortAggr extends SortAggr with TestAsync_mysql
object SortBasics extends SortBasics with TestAsync_mysql
object SortDynamic extends SortDynamic with TestAsync_mysql
object SortExprOpt extends SortExprOpt with TestAsync_mysql
object SortNested extends SortNested with TestAsync_mysql

