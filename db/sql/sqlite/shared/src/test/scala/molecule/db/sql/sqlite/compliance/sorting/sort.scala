package molecule.db.sql.sqlite.compliance.sorting

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.sorting.*
import molecule.db.sql
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class SortAggrTest extends Test {
  SortAggr(this, Api_sqlite_async)
}
class SortBasicsTest extends Test {
  SortBasics(this, Api_sqlite_async)
}
class SortDynamicTest extends Test {
  SortDynamic(this, Api_sqlite_async)
}
class SortNestedTest extends Test {
  SortNested(this, Api_sqlite_async)
}

