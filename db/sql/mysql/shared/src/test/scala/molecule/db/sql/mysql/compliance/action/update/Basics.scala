package molecule.db.sql.mysql.compliance.action.update

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.action.update.Basics
import molecule.db.sql.mysql.setup.Api_mysql_async

class BasicsTest extends Test {
  Basics(this, Api_mysql_async)
}
