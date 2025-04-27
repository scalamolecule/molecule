package molecule.db.sql.mysql.compliance.action.update

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.update.*
import molecule.db.sql
import molecule.db.sql.mysql.setup.Api_mysql_async

class BasicsTest extends Test {
  Basics(this, Api_mysql_async)
}
