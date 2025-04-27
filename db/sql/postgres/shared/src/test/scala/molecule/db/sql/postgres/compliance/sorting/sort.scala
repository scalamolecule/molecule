package molecule.db.sql.postgres.compliance.sorting

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.sorting.*
import molecule.db.sql
import molecule.db.sql.postgres.setup.Api_postgres_async

class SortAggrTest extends Test {
  SortAggr(this, Api_postgres_async)
}
class SortBasicsTest extends Test {
  SortBasics(this, Api_postgres_async)
}
class SortDynamicTest extends Test {
  SortDynamic(this, Api_postgres_async)
}
class SortNestedTest extends Test {
  SortNested(this, Api_postgres_async)
}

