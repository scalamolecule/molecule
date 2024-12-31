package molecule.sql.sqlite.compliance.filterAttr.seq

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.filterAttr.seq._
import molecule.sql.sqlite.setup.Api_sqlite_async

class Adjacent extends Test {
  Adjacent(this, Api_sqlite_async)
}
class CrossNs extends Test {
  CrossNs(this, Api_sqlite_async)
}
class Types extends Test {
  Types(this, Api_sqlite_async)
}
