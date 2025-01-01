package molecule.sql.h2.compliance.action.update.filter

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.update.filter._
import molecule.sql.h2.setup.Api_h2_async

class FilterOne extends Test {
  FilterOne(this, Api_h2_async)
}
class FilterSet extends Test {
  FilterSet(this, Api_h2_async)
}
class FilterSeq extends Test {
  FilterSeq(this, Api_h2_async)
}
class FilterMap extends Test {
  FilterMap(this, Api_h2_async)
}
