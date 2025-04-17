package molecule.sql.mysql.compliance.sorting

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.sorting._
import molecule.sql.mysql.setup.Api_mysql_async

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

