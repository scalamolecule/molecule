package molecule.db.sql.h2.compliance.sorting

import molecule.core.setup.MUnit
import molecule.db.compliance.test.sorting.{SortAggr, SortBasics, SortDynamic, SortNested}
import molecule.db.sql.h2.setup.Api_h2_async

class SortAggrTest extends MUnit {
  SortAggr(this, Api_h2_async)
}
class SortBasicsTest extends MUnit {
  SortBasics(this, Api_h2_async)
}
class SortDynamicTest extends MUnit {
  SortDynamic(this, Api_h2_async)
}
class SortNestedTest extends MUnit {
  SortNested(this, Api_h2_async)
}

