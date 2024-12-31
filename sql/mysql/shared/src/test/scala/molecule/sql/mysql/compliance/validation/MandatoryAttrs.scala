package molecule.sql.mysql.compliance.validation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.validation._
import molecule.sql.mysql.setup.Api_mysql_async

class MandatoryAttrs extends Test {
  MandatoryAttrs(this, Api_mysql_async)
}
