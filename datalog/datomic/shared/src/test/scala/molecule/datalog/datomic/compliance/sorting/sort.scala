package molecule.datalog.datomic.compliance.sorting

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.sorting._
import molecule.datalog.datomic.setup.Api_datomic_async

class SortAggr extends Test {
  SortAggr(this, Api_datomic_async)
}
class SortBasics extends Test {
  SortBasics(this, Api_datomic_async)
}
class SortDynamic extends Test {
  SortDynamic(this, Api_datomic_async)
}
class SortNested extends Test {
  SortNested(this, Api_datomic_async)
}
