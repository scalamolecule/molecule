package molecule.db.sqlite.compliance.crud.update.filter

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.crud.update.filter.{FilterMap, FilterOne, FilterSeq, FilterSet}
import molecule.db.sqlite.setup.Api_sqlite_async

class FilterOneTest extends MUnit {
  FilterOne(this, Api_sqlite_async)
}
class FilterSetTest extends MUnit {
  FilterSet(this, Api_sqlite_async)
}
class FilterSeqTest extends MUnit {
  FilterSeq(this, Api_sqlite_async)
}
class FilterMapTest extends MUnit {
  FilterMap(this, Api_sqlite_async)
}
