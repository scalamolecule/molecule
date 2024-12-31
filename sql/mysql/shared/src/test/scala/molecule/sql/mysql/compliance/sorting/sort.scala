package molecule.sql.mysql.compliance.sorting

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.sorting._
import molecule.sql.mysql.setup.Api_mysql_async

class SortAggr extends Test {
  SortAggr(this, Api_mysql_async)
}
class SortBasics extends Test {
  SortBasics(this, Api_mysql_async)
}
class SortDynamic extends Test {
  SortDynamic(this, Api_mysql_async)
}
class SortNested extends Test {
  SortNested(this, Api_mysql_async)
}

