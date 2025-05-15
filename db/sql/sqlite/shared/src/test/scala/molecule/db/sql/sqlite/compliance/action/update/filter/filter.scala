package molecule.db.sql.sqlite.compliance.action.update.filter

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.action.update.filter.{FilterMap, FilterOne, FilterSeq, FilterSet}
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class FilterOneTest extends Test {
  FilterOne(this, Api_sqlite_async)
}
class FilterSetTest extends Test {
  FilterSet(this, Api_sqlite_async)
}
class FilterSeqTest extends Test {
  FilterSeq(this, Api_sqlite_async)
}
class FilterMapTest extends Test {
  FilterMap(this, Api_sqlite_async)
}
