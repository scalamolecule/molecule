package molecule.db.sql.mysql.compliance.pagination.cursor

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.pagination.SharedSemantics
import molecule.db.sql.mysql.setup.Api_mysql_async

class SharedSemanticsTest extends Test {
  SharedSemantics(this, Api_mysql_async)
}
