package molecule.sql.sqlite.compliance.sort

import molecule.coreTests.spi.sort._
import molecule.sql.sqlite.setup.TestAsync_sqlite

object Test_SortAggr extends SortAggr with TestAsync_sqlite
object Test_SortBasics extends SortBasics with TestAsync_sqlite
object Test_SortDynamic extends SortDynamic with TestAsync_sqlite
object Test_SortNested extends SortNested with TestAsync_sqlite

