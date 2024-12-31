package molecule.sql.mariadb.compliance.sorting

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.sorting._
import molecule.sql.mariadb.setup.Api_mariadb_async

class SortAggr extends Test {
  SortAggr(this, Api_mariadb_async)
}
class SortBasics extends Test {
  SortBasics(this, Api_mariadb_async)
}
class SortDynamic extends Test {
  SortDynamic(this, Api_mariadb_async)
}
class SortNested extends Test {
  SortNested(this, Api_mariadb_async)
}

