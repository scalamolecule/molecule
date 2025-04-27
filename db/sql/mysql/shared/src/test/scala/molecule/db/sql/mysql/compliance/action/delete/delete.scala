package molecule.db.sql.mysql.compliance.action.delete

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.delete.*
import molecule.db.sql
import molecule.db.sql.mysql.setup.Api_mysql_async

class Delete_idTest extends Test {
  Delete_id(this, Api_mysql_async)
}
class Delete_filterTest extends Test {
  Delete_filter(this, Api_mysql_async)
}
