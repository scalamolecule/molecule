package molecule.sql.mariadb.compliance.sorting

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.sorting._
import molecule.sql.mariadb.setup.Api_mariadb_async

class SortAggr extends MUnitSuite {
  SortAggr(this, Api_mariadb_async)
}
class SortBasics extends MUnitSuite {
  SortBasics(this, Api_mariadb_async)
}
class SortDynamic extends MUnitSuite {
  SortDynamic(this, Api_mariadb_async)
}
class SortNested extends MUnitSuite {
  SortNested(this, Api_mariadb_async)
}

