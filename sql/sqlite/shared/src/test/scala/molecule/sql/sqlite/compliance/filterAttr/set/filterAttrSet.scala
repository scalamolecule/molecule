package molecule.sql.sqlite.compliance.filterAttr.set

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.filterAttr.set._
import molecule.sql.sqlite.setup.Api_sqlite_async

class Adjacent extends MUnitSuite {
  Adjacent(this, Api_sqlite_async)
}
class CrossNs extends MUnitSuite {
  CrossNs(this, Api_sqlite_async)
}
class Types extends MUnitSuite {
  Types(this, Api_sqlite_async)
}
