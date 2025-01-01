package molecule.sql.sqlite.compliance.action.update.filter

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.update.filter._
import molecule.sql.sqlite.setup.Api_sqlite_async

class FilterOne extends Test {
  FilterOne(this, Api_sqlite_async)
}
class FilterSet extends Test {
  FilterSet(this, Api_sqlite_async)
}
class FilterSeq extends Test {
  FilterSeq(this, Api_sqlite_async)
}
class FilterMap extends Test {
  FilterMap(this, Api_sqlite_async)
}
