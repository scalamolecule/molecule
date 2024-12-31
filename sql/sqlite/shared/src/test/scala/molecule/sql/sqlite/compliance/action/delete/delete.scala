package molecule.sql.sqlite.compliance.action.delete

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.action.delete._
import molecule.sql.sqlite.setup.Api_sqlite_async

class Delete_id extends MUnitSuite {
  Delete_id(this, Api_sqlite_async)
}
class Delete_filter extends MUnitSuite {
  Delete_filter(this, Api_sqlite_async)
}
