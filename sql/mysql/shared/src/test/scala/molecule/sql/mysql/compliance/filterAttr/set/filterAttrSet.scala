package molecule.sql.mysql.compliance.filterAttr.set

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.filterAttr.set._
import molecule.sql.mysql.setup.Api_mysql_async

class Adjacent extends MUnitSuite {
  Adjacent(this, Api_mysql_async)
}
class CrossNs extends MUnitSuite {
  CrossNs(this, Api_mysql_async)
}
class Types extends MUnitSuite {
  Types(this, Api_mysql_async)
}
