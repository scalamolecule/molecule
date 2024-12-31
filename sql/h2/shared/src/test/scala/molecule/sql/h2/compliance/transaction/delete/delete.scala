package molecule.sql.h2.compliance.transaction.delete

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.action.delete._
import molecule.sql.h2.setup.Api_h2_async

class Delete_id extends MUnitSuite {
  Delete_id(this, Api_h2_async)
}
class Delete_filter extends MUnitSuite {
  Delete_filter(this, Api_h2_async)
}
