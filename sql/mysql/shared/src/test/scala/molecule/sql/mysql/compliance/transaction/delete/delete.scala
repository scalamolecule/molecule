package molecule.sql.mysql.compliance.transaction.delete

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.action.delete._
import molecule.sql.mysql.setup.Api_mysql_async

class Delete_id extends MUnitSuite {
  Delete_id(this, Api_mysql_async)
}
class Delete_filter extends MUnitSuite {
  Delete_filter(this, Api_mysql_async)
}
