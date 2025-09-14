package molecule.db.mysql.compliance.crud.update

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.crud.update.Basics
import molecule.db.mysql.setup.Api_mysql_async

class BasicsTest extends MUnit {
  Basics(this, Api_mysql_async)
}
