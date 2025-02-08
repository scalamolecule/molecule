package molecule.sql.mariadb.compliance.filterAttr.set

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.filterAttr.set._
import molecule.sql.mariadb.setup.Api_mariadb_async

class Adjacent extends Test {
  Adjacent(this, Api_mariadb_async)
}
class CrossEntity extends Test {
  CrossEntity(this, Api_mariadb_async)
}
class Types extends Test {
  Types(this, Api_mariadb_async)
}
