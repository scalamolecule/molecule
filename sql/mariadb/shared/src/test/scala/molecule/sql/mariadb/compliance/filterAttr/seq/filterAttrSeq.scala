package molecule.sql.mariadb.compliance.filterAttr.seq

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.filterAttr.seq._
import molecule.sql.mariadb.setup.Api_mariadb_async

class Adjacent extends Test {
  Adjacent(this, Api_mariadb_async)
}
class CrossNs extends Test {
  CrossNs(this, Api_mariadb_async)
}
class Types extends Test {
  Types(this, Api_mariadb_async)
}
