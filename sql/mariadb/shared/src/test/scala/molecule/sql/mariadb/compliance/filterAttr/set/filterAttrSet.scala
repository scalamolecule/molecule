package molecule.sql.mariadb.compliance.filterAttr.set

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.filterAttr.set._
import molecule.sql.mariadb.setup.Api_mariadb_async

class Adjacent extends MUnitSuite {
  Adjacent(this, Api_mariadb_async)
}
class CrossNs extends MUnitSuite {
  CrossNs(this, Api_mariadb_async)
}
class Types extends MUnitSuite {
  Types(this, Api_mariadb_async)
}
