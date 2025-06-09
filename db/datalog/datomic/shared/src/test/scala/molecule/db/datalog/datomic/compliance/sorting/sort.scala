package molecule.db.datalog.datomic.compliance.sorting

import molecule.core.setup.MUnit
import molecule.db.compliance.test.sorting.{SortAggr, SortBasics, SortDynamic, SortNested}
import molecule.db.datalog.datomic.setup.Api_datomic_async

class SortAggrTest extends MUnit {
  SortAggr(this, Api_datomic_async)
}
class SortBasicsTest extends MUnit {
  SortBasics(this, Api_datomic_async)
}
class SortDynamicTest extends MUnit {
  SortDynamic(this, Api_datomic_async)
}
class SortNestedTest extends MUnit {
  SortNested(this, Api_datomic_async)
}
