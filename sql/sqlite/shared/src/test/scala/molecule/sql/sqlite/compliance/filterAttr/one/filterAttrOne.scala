package molecule.sql.sqlite.compliance.filterAttr.one

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.filterAttr.one._
import molecule.sql.sqlite.setup.Api_sqlite_async

class Adjacent extends Test {
  Adjacent(this, Api_sqlite_async)
}
class CrossNs extends Test {
  CrossNs(this, Api_sqlite_async)
}
class FilterAttr_id extends Test {
  FilterAttr_id(this, Api_sqlite_async)
}
class FilterAttrNested extends Test {
  FilterAttrNested(this, Api_sqlite_async)
}
class FilterAttrRef extends Test {
  FilterAttrRef(this, Api_sqlite_async)
}
class Semantics extends Test {
  Semantics(this, Api_sqlite_async)
}
class Sorting extends Test {
  Sorting(this, Api_sqlite_async)
}
class Types extends Test {
  Types(this, Api_sqlite_async)
}
