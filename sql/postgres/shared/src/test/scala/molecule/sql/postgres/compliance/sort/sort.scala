package molecule.sql.postgres.compliance.sort

import molecule.coreTests.spi.sort._
import molecule.sql.postgres.setup.TestAsync_postgres

object Test_SortAggr extends SortAggr with TestAsync_postgres
object Test_SortBasics extends SortBasics with TestAsync_postgres
object Test_SortDynamic extends SortDynamic with TestAsync_postgres
object Test_SortNested extends SortNested with TestAsync_postgres

