package molecule.db.sql.sqlite.compliance.action.delete

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.delete.*
import molecule.db.sql
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class Delete_idTest extends Test {
  Delete_id(this, Api_sqlite_async)
}
class Delete_filterTest extends Test {
  Delete_filter(this, Api_sqlite_async)
}
