package molecule.db.datalog.datomic.compliance.action.delete

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.delete.*
import molecule.db.datalog
import molecule.db.datalog.datomic.setup.Api_datomic_async

class Delete_idTest extends Test {
  Delete_id(this, Api_datomic_async)
}
class Delete_filterTest extends Test {
  Delete_filter(this, Api_datomic_async)
}
