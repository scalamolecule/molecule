package molecule.sql.sqlite.compliance.action.update.filter

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.update.filter.*
import molecule.sql.sqlite.setup.Api_sqlite_async

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
