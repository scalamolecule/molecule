package molecule.sql.mysql.compliance.sort

import molecule.coreTests.spi.sort._
import molecule.sql.mysql.setup.Test_mysql_async

object Test_SortAggr extends SortAggr with Test_mysql_async
object Test_SortBasics extends SortBasics with Test_mysql_async
object Test_SortDynamic extends SortDynamic with Test_mysql_async
object Test_SortNested extends SortNested with Test_mysql_async

