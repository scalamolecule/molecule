package molecule.sql.postgres.compliance.action.delete

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.delete._
import molecule.sql.postgres.setup.Api_postgres_async

class Delete_id extends Test {
  Delete_id(this, Api_postgres_async)
}
class Delete_filter extends Test {
  Delete_filter(this, Api_postgres_async)
}