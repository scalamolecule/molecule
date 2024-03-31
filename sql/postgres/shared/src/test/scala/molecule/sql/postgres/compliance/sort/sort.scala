package molecule.sql.postgres.compliance.sort

import molecule.coreTests.spi.sort._
import molecule.sql.postgres.setup.TestAsync_postgres

object SortAggr extends SortAggr with TestAsync_postgres
object SortBasics extends SortBasics with TestAsync_postgres
object SortDynamic extends SortDynamic with TestAsync_postgres
object SortNested extends SortNested with TestAsync_postgres

