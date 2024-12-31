package molecule.sql.sqlite.compliance.sorting

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.sorting._
import molecule.sql.sqlite.setup.Api_sqlite_async

class SortAggr extends MUnitSuite {
  SortAggr(this, Api_sqlite_async)
}
class SortBasics extends MUnitSuite {
  SortBasics(this, Api_sqlite_async)
}
class SortDynamic extends MUnitSuite {
  SortDynamic(this, Api_sqlite_async)
}
class SortNested extends MUnitSuite {
  SortNested(this, Api_sqlite_async)
}

