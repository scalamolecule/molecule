package molecule.sql.mysql.compliance.validation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.validation._
import molecule.sql.mysql.setup.Api_mysql_async

class RequiredAttrs extends Test {
  RequiredAttrs(this, Api_mysql_async)
}
