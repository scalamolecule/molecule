package molecule.datalog.datomic.compliance.sorting

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.sorting._
import molecule.datalog.datomic.setup.Api_datomic_async

class SortAggrTest extends Test {
  SortAggr(this, Api_datomic_async)
}
class SortBasicsTest extends Test {
  SortBasics(this, Api_datomic_async)
}
class SortDynamicTest extends Test {
  SortDynamic(this, Api_datomic_async)
}
class SortNestedTest extends Test {
  SortNested(this, Api_datomic_async)
}
