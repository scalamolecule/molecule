package molecule.db.sql.mysql.compliance.sorting

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.sorting.{SortAggr, SortBasics, SortDynamic, SortNested}
import molecule.db.sql
import molecule.db.sql.mysql.setup.Api_mysql_async

class SortAggrTest extends Test {
  SortAggr(this, Api_mysql_async)
}
class SortBasicsTest extends Test {
  SortBasics(this, Api_mysql_async)
}
class SortDynamicTest extends Test {
  SortDynamic(this, Api_mysql_async)
}
class SortNestedTest extends Test {
  SortNested(this, Api_mysql_async)
}

