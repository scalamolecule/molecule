package molecule.datalog.datomic.compliance.transaction.delete

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.transaction.delete._
import molecule.datalog.datomic.setup.Api_datomic_async

class Delete_id extends Test {
  Delete_id(this, Api_datomic_async)
}
class Delete_filter extends Test {
  Delete_filter(this, Api_datomic_async)
}
