package molecule.sql.sqlite.compliance.filterAttr.set

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.filterAttr.set._
import molecule.sql.sqlite.setup.Api_sqlite_async

class Adjacent extends Test {
  Adjacent(this, Api_sqlite_async)
}
class CrossEntity extends Test {
  CrossEntity(this, Api_sqlite_async)
}
class Types extends Test {
  Types(this, Api_sqlite_async)
}
