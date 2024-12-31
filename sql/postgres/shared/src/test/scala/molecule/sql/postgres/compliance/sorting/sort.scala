package molecule.sql.postgres.compliance.sorting

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.sorting._
import molecule.sql.postgres.setup.Api_postgres_async

class SortAggr extends MUnitSuite {
  SortAggr(this, Api_postgres_async)
}
class SortBasics extends MUnitSuite {
  SortBasics(this, Api_postgres_async)
}
class SortDynamic extends MUnitSuite {
  SortDynamic(this, Api_postgres_async)
}
class SortNested extends MUnitSuite {
  SortNested(this, Api_postgres_async)
}

