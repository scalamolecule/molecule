package molecule.sql.mariadb.compliance.transaction.delete

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.delete._
import molecule.sql.mariadb.setup.Api_mariadb_async

class Delete_id extends Test {
  Delete_id(this, Api_mariadb_async)
}
class Delete_filter extends Test {
  Delete_filter(this, Api_mariadb_async)
}
