package molecule.datalog.datomic.compliance.transaction.delete

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.action.delete._
import molecule.datalog.datomic.setup.Api_datomic_async

class Delete_id extends MUnitSuite {
  Delete_id(this, Api_datomic_async)
}
class Delete_filter extends MUnitSuite {
  Delete_filter(this, Api_datomic_async)
}
