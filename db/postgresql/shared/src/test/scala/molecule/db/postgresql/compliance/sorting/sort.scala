package molecule.db.postgresql.compliance.sorting

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.sorting.{SortAggr, SortBasics, SortDynamic, SortNested}
import molecule.db.postgresql.setup.Api_postgresql_async

class SortAggrTest extends MUnit {
  SortAggr(this, Api_postgresql_async)
}
class SortBasicsTest extends MUnit {
  SortBasics(this, Api_postgresql_async)
}
class SortDynamicTest extends MUnit {
  SortDynamic(this, Api_postgresql_async)
}
class SortNestedTest extends MUnit {
  SortNested(this, Api_postgresql_async)
}

