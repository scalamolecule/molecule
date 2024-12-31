package molecule.datalog.datomic.compliance.entityName

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.entityName.Scoped
import molecule.datalog.datomic.setup.Api_datomic_async

class Scoped extends MUnitSuite {
  Scoped(this, Api_datomic_async)
}
