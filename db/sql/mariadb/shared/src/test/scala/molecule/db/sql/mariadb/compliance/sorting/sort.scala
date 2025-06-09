package molecule.db.sql.mariadb.compliance.sorting

import molecule.core.setup.MUnit
import molecule.db.compliance.test.sorting.{SortAggr, SortBasics, SortDynamic, SortNested}
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class SortAggrTest extends MUnit {
  SortAggr(this, Api_mariadb_async)
}
class SortBasicsTest extends MUnit {
  SortBasics(this, Api_mariadb_async)
}
class SortDynamicTest extends MUnit {
  SortDynamic(this, Api_mariadb_async)
}
class SortNestedTest extends MUnit {
  SortNested(this, Api_mariadb_async)
}

