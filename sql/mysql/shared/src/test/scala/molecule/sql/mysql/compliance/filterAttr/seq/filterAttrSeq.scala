package molecule.sql.mysql.compliance.filterAttr.seq

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.filterAttr.seq._
import molecule.sql.mysql.setup.Api_mysql_async

class Adjacent extends Test {
  Adjacent(this, Api_mysql_async)
}
class CrossEntity extends Test {
  CrossEntity(this, Api_mysql_async)
}
class Types extends Test {
  Types(this, Api_mysql_async)
}
