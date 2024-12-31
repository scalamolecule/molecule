package molecule.datalog.datomic.compliance.sorting

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.sorting._
import molecule.datalog.datomic.setup.Api_datomic_async

class SortAggr extends MUnitSuite {
  SortAggr(this, Api_datomic_async)
}
class SortBasics extends MUnitSuite {
  SortBasics(this, Api_datomic_async)
}
class SortDynamic extends MUnitSuite {
  SortDynamic(this, Api_datomic_async)
}
class SortNested extends MUnitSuite {
  SortNested(this, Api_datomic_async)
}
