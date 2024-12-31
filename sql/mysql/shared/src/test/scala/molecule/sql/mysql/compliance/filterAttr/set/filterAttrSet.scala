package molecule.sql.mysql.compliance.filterAttr.set

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.filterAttr.set._
import molecule.sql.mysql.setup.Api_mysql_async

class Adjacent extends Test {
  Adjacent(this, Api_mysql_async)
}
class CrossNs extends Test {
  CrossNs(this, Api_mysql_async)
}
class Types extends Test {
  Types(this, Api_mysql_async)
}
