package molecule.db.sql.h2.compliance.action.update.filter

import molecule.core.setup.MUnit
import molecule.db.compliance.test.action.update.filter.{FilterMap, FilterOne, FilterSeq, FilterSet}
import molecule.db.sql.h2.setup.Api_h2_async

class FilterOneTest extends MUnit {
  FilterOne(this, Api_h2_async)
}
class FilterSetTest extends MUnit {
  FilterSet(this, Api_h2_async)
}
class FilterSeqTest extends MUnit {
  FilterSeq(this, Api_h2_async)
}
class FilterMapTest extends MUnit {
  FilterMap(this, Api_h2_async)
}
