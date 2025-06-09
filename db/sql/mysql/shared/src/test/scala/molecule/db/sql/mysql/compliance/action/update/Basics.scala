package molecule.db.sql.mysql.compliance.action.update

import molecule.core.setup.MUnit
import molecule.db.compliance.test.action.update.Basics
import molecule.db.sql.mysql.setup.Api_mysql_async

class BasicsTest extends MUnit {
  Basics(this, Api_mysql_async)
}
