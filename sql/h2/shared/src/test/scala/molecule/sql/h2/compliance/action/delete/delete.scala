package molecule.sql.h2.compliance.action.delete

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.delete._
import molecule.sql.h2.setup.Api_h2_async

class Delete_id extends Test {
  Delete_id(this, Api_h2_async)
}
class Delete_filter extends Test {
  Delete_filter(this, Api_h2_async)
}
