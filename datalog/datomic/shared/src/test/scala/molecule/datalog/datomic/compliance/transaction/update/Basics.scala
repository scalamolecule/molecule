package molecule.datalog.datomic.compliance.transaction.update

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.action.update._
import molecule.datalog.datomic.setup.Api_datomic_async

class Basics extends MUnitSuite {
  Basics(this, Api_datomic_async)
}
