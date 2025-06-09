package molecule.db.datalog.datomic.compliance.action.update.filter

import molecule.core.setup.MUnit
import molecule.db.compliance.test.action.update.filter.{FilterMap, FilterOne, FilterSeq, FilterSet}
import molecule.db.datalog.datomic.setup.Api_datomic_async

class FilterOneTest extends MUnit {
  FilterOne(this, Api_datomic_async)
}
class FilterSetTest extends MUnit {
  FilterSet(this, Api_datomic_async)
}
class FilterSeqTest extends MUnit {
  FilterSeq(this, Api_datomic_async)
}
class FilterMapTest extends MUnit {
  FilterMap(this, Api_datomic_async)
}
