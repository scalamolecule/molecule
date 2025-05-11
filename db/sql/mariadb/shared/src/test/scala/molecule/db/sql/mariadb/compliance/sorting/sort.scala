package molecule.db.sql.mariadb.compliance.sorting

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.sorting.{SortAggr, SortBasics, SortDynamic, SortNested}
import molecule.db.sql
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class SortAggrTest extends Test {
  SortAggr(this, Api_mariadb_async)
}
class SortBasicsTest extends Test {
  SortBasics(this, Api_mariadb_async)
}
class SortDynamicTest extends Test {
  SortDynamic(this, Api_mariadb_async)
}
class SortNestedTest extends Test {
  SortNested(this, Api_mariadb_async)
}

