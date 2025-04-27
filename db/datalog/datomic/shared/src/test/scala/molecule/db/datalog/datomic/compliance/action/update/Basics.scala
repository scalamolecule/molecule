package molecule.db.datalog.datomic.compliance.action.update

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.update.*
import molecule.db.datalog
import molecule.db.datalog.datomic.setup.Api_datomic_async

class BasicsTest extends Test {
  Basics(this, Api_datomic_async)
}
