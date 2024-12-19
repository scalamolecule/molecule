package molecule.sql.mariadb.compliance.sorting

import molecule.coreTests.spi.sorting._
import molecule.sql.mariadb.setup.Test_mariadb_async

object Test_SortAggr extends SortAggr with Test_mariadb_async
object Test_SortBasics extends SortBasics with Test_mariadb_async
object Test_SortDynamic extends SortDynamic with Test_mariadb_async
object Test_SortNested extends SortNested with Test_mariadb_async

