package molecule.sql.sqlite.compliance.sort

import molecule.coreTests.spi.sort._
import molecule.sql.sqlite.setup.TestAsync_sqlite

object SortAggr extends SortAggr with TestAsync_sqlite
object SortBasics extends SortBasics with TestAsync_sqlite
object SortDynamic extends SortDynamic with TestAsync_sqlite
object SortNested extends SortNested with TestAsync_sqlite

