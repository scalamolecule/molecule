package molecule.sql.sqlite.compliance.sorting

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.sorting._
import molecule.sql.sqlite.setup.Api_sqlite_async

class SortAggr extends Test {
  SortAggr(this, Api_sqlite_async)
}
class SortBasics extends Test {
  SortBasics(this, Api_sqlite_async)
}
class SortDynamic extends Test {
  SortDynamic(this, Api_sqlite_async)
}
class SortNested extends Test {
  SortNested(this, Api_sqlite_async)
}

