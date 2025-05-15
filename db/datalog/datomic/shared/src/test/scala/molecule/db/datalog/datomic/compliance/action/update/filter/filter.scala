package molecule.db.datalog.datomic.compliance.action.update.filter

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.action.update.filter.{FilterMap, FilterOne, FilterSeq, FilterSet}
import molecule.db.datalog.datomic.setup.Api_datomic_async

class FilterOneTest extends Test {
  FilterOne(this, Api_datomic_async)
}
class FilterSetTest extends Test {
  FilterSet(this, Api_datomic_async)
}
class FilterSeqTest extends Test {
  FilterSeq(this, Api_datomic_async)
}
class FilterMapTest extends Test {
  FilterMap(this, Api_datomic_async)
}
