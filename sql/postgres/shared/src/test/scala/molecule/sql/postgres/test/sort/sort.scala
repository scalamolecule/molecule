package molecule.sql.postgres.test.sort

import molecule.coreTests.test.sort._
import molecule.sql.postgres.setup.TestAsync_postgres

object SortAggr extends SortAggr with TestAsync_postgres
object SortBasics extends SortBasics with TestAsync_postgres
object SortDynamic extends SortDynamic with TestAsync_postgres
object SortExprOpt extends SortExprOpt with TestAsync_postgres
object SortNested extends SortNested with TestAsync_postgres

