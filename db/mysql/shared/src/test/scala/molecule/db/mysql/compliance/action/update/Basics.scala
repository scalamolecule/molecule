package molecule.db.mysql.compliance.action.update

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.action.update.Basics
import molecule.db.mysql.setup.Api_mysql_async

class BasicsTest extends MUnit {
  Basics(this, Api_mysql_async)
}
