package molecule.sql.mariadb.compliance.filterAttr.seq

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.filterAttr.seq.*
import molecule.sql.mariadb.setup.Api_mariadb_async

class AdjacentTest extends Test {
  Adjacent(this, Api_mariadb_async)
}
class CrossEntityTest extends Test {
  CrossEntity(this, Api_mariadb_async)
}
class TypesTest extends Test {
  Types(this, Api_mariadb_async)
}
