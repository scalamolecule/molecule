package molecule.datalog.datomic.compliance.action.update.filter

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.update.filter._
import molecule.datalog.datomic.setup.Api_datomic_async

class FilterOne extends Test {
  FilterOne(this, Api_datomic_async)
}
class FilterSet extends Test {
  FilterSet(this, Api_datomic_async)
}
class FilterSeq extends Test {
  FilterSeq(this, Api_datomic_async)
}
class FilterMap extends Test {
  FilterMap(this, Api_datomic_async)
}
