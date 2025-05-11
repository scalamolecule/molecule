package molecule.db.datalog.datomic.compliance.sorting

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.sorting.{SortAggr, SortBasics, SortDynamic, SortNested}
import molecule.db.datalog
import molecule.db.datalog.datomic.setup.Api_datomic_async

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
