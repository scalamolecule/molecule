package molecule.db.sql.mariadb.compliance.sorting

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.sorting.*
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

