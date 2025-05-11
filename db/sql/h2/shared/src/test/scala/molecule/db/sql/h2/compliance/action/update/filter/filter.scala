package molecule.db.sql.h2.compliance.action.update.filter

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.action.update.filter.{FilterMap, FilterOne, FilterSeq, FilterSet}
import molecule.db.sql
import molecule.db.sql.h2.setup.Api_h2_async

class FilterOneTest extends Test {
  FilterOne(this, Api_h2_async)
}
class FilterSetTest extends Test {
  FilterSet(this, Api_h2_async)
}
class FilterSeqTest extends Test {
  FilterSeq(this, Api_h2_async)
}
class FilterMapTest extends Test {
  FilterMap(this, Api_h2_async)
}
