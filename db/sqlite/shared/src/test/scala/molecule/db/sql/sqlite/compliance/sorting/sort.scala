package molecule.db.sql.sqlite.compliance.sorting

import molecule.core.setup.MUnit
import molecule.db.compliance.test.sorting.{SortAggr, SortBasics, SortDynamic, SortNested}
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class SortAggrTest extends MUnit {
  SortAggr(this, Api_sqlite_async)
}
class SortBasicsTest extends MUnit {
  SortBasics(this, Api_sqlite_async)
}
class SortDynamicTest extends MUnit {
  SortDynamic(this, Api_sqlite_async)
}
class SortNestedTest extends MUnit {
  SortNested(this, Api_sqlite_async)
}

