package molecule.sql.postgres.compliance.sorting

import molecule.coreTests.spi.sorting._
import molecule.sql.postgres.setup.Test_postgres_async

object Test_SortAggr extends SortAggr with Test_postgres_async
object Test_SortBasics extends SortBasics with Test_postgres_async
object Test_SortDynamic extends SortDynamic with Test_postgres_async
object Test_SortNested extends SortNested with Test_postgres_async

