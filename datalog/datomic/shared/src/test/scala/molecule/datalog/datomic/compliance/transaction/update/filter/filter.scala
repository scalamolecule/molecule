package molecule.datalog.datomic.compliance.transaction.update.filter

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.action.update.filter._
import molecule.datalog.datomic.setup.Api_datomic_async

class FilterOne extends MUnitSuite {
  FilterOne(this, Api_datomic_async)
}
class FilterSet extends MUnitSuite {
  FilterSet(this, Api_datomic_async)
}
class FilterSeq extends MUnitSuite {
  FilterSeq(this, Api_datomic_async)
}
class FilterMap extends MUnitSuite {
  FilterMap(this, Api_datomic_async)
}
