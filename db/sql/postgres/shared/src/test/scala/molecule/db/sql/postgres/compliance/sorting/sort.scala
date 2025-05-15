package molecule.db.sql.postgres.compliance.sorting

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.sorting.{SortAggr, SortBasics, SortDynamic, SortNested}
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

