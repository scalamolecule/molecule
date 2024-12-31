package molecule.sql.sqlite.compliance.action.update.filter

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.action.update.filter._
import molecule.sql.sqlite.setup.Api_sqlite_async

class FilterOne extends MUnitSuite {
  FilterOne(this, Api_sqlite_async)
}
class FilterSet extends MUnitSuite {
  FilterSet(this, Api_sqlite_async)
}
class FilterSeq extends MUnitSuite {
  FilterSeq(this, Api_sqlite_async)
}
class FilterMap extends MUnitSuite {
  FilterMap(this, Api_sqlite_async)
}
