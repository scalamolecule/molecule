package molecule.db.sql.h2.compliance.sorting

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.sorting.{SortAggr, SortBasics, SortDynamic, SortNested}
import molecule.db.sql
import molecule.db.sql.h2.setup.Api_h2_async

class SortAggrTest extends Test {
  SortAggr(this, Api_h2_async)
}
class SortBasicsTest extends Test {
  SortBasics(this, Api_h2_async)
}
class SortDynamicTest extends Test {
  SortDynamic(this, Api_h2_async)
}
class SortNestedTest extends Test {
  SortNested(this, Api_h2_async)
}

