package molecule.sql.mariadb.compliance.transaction.update.filter

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.transaction.update.filter._
import molecule.sql.mariadb.setup.Api_mariadb_async

class FilterOne extends Test {
  FilterOne(this, Api_mariadb_async)
}
class FilterSet extends Test {
  FilterSet(this, Api_mariadb_async)
}
class FilterSeq extends Test {
  FilterSeq(this, Api_mariadb_async)
}
class FilterMap extends Test {
  FilterMap(this, Api_mariadb_async)
}
