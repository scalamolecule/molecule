package molecule.db.mysql.compliance.sorting

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.sorting.{SortAggr, SortBasics, SortDynamic, SortNested}
import molecule.db.mysql.setup.Api_mysql_async

class SortAggrTest extends MUnit {
  SortAggr(this, Api_mysql_async)
}
class SortBasicsTest extends MUnit {
  SortBasics(this, Api_mysql_async)
}
class SortDynamicTest extends MUnit {
  SortDynamic(this, Api_mysql_async)
}
class SortNestedTest extends MUnit {
  SortNested(this, Api_mysql_async)
}

