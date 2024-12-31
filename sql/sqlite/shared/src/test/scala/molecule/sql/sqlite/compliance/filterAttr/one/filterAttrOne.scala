package molecule.sql.sqlite.compliance.filterAttr.one

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.filterAttr.one._
import molecule.sql.sqlite.setup.Api_sqlite_async

class Adjacent extends MUnitSuite {
  Adjacent(this, Api_sqlite_async)
}
class CrossNs extends MUnitSuite {
  CrossNs(this, Api_sqlite_async)
}
class FilterAttr_id extends MUnitSuite {
  FilterAttr_id(this, Api_sqlite_async)
}
class FilterAttrNested extends MUnitSuite {
  FilterAttrNested(this, Api_sqlite_async)
}
class FilterAttrRef extends MUnitSuite {
  FilterAttrRef(this, Api_sqlite_async)
}
class Semantics extends MUnitSuite {
  Semantics(this, Api_sqlite_async)
}
class Sorting extends MUnitSuite {
  Sorting(this, Api_sqlite_async)
}
class Types extends MUnitSuite {
  Types(this, Api_sqlite_async)
}
