package molecule.sql.mysql.compliance.sorting

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.sorting._
import molecule.sql.mysql.setup.Api_mysql_async

class SortAggr extends MUnitSuite {
  SortAggr(this, Api_mysql_async)
}
class SortBasics extends MUnitSuite {
  SortBasics(this, Api_mysql_async)
}
class SortDynamic extends MUnitSuite {
  SortDynamic(this, Api_mysql_async)
}
class SortNested extends MUnitSuite {
  SortNested(this, Api_mysql_async)
}

