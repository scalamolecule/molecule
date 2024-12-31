package molecule.sql.postgres.compliance.sorting

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.sorting._
import molecule.sql.postgres.setup.Api_postgres_async

class SortAggr extends Test {
  SortAggr(this, Api_postgres_async)
}
class SortBasics extends Test {
  SortBasics(this, Api_postgres_async)
}
class SortDynamic extends Test {
  SortDynamic(this, Api_postgres_async)
}
class SortNested extends Test {
  SortNested(this, Api_postgres_async)
}

