package molecule.sql.sqlite.compliance.action.delete

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.delete._
import molecule.sql.sqlite.setup.Api_sqlite_async

class Delete_id extends Test {
  Delete_id(this, Api_sqlite_async)
}
class Delete_filter extends Test {
  Delete_filter(this, Api_sqlite_async)
}
