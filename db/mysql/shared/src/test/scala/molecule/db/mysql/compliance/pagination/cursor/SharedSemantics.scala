package molecule.db.mysql.compliance.pagination.cursor

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.pagination.SharedSemantics
import molecule.db.mysql.setup.Api_mysql_async

class SharedSemanticsTest extends MUnit {
  SharedSemantics(this, Api_mysql_async)
}
