package molecule.sql.sqlite.compliance.sorting

import molecule.coreTests.spi.sorting._
import molecule.sql.sqlite.setup.Test_sqlite_async

object Test_SortAggr extends SortAggr with Test_sqlite_async
object Test_SortBasics extends SortBasics with Test_sqlite_async
object Test_SortDynamic extends SortDynamic with Test_sqlite_async
object Test_SortNested extends SortNested with Test_sqlite_async

