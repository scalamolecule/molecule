package molecule.sql.mariadb.compliance.transaction.update.filter

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.action.update.filter._
import molecule.sql.mariadb.setup.Api_mariadb_async

class FilterOne extends MUnitSuite {
  FilterOne(this, Api_mariadb_async)
}
class FilterSet extends MUnitSuite {
  FilterSet(this, Api_mariadb_async)
}
class FilterSeq extends MUnitSuite {
  FilterSeq(this, Api_mariadb_async)
}
class FilterMap extends MUnitSuite {
  FilterMap(this, Api_mariadb_async)
}
