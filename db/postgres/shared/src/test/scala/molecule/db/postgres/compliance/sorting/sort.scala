package molecule.db.postgres.compliance.sorting

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.sorting.{SortAggr, SortBasics, SortDynamic, SortNested}
import molecule.db.postgres.setup.Api_postgres_async

class SortAggrTest extends MUnit {
  SortAggr(this, Api_postgres_async)
}
class SortBasicsTest extends MUnit {
  SortBasics(this, Api_postgres_async)
}
class SortDynamicTest extends MUnit {
  SortDynamic(this, Api_postgres_async)
}
class SortNestedTest extends MUnit {
  SortNested(this, Api_postgres_async)
}

