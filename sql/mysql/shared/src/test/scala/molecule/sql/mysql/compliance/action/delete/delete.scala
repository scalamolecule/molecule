package molecule.sql.mysql.compliance.action.delete

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.delete._
import molecule.sql.mysql.setup.Api_mysql_async

class Delete_id extends Test {
  Delete_id(this, Api_mysql_async)
}
class Delete_filter extends Test {
  Delete_filter(this, Api_mysql_async)
}