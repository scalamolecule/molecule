package molecule.datalog.datomic.compliance.entityName

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.entityName._
import molecule.datalog.datomic.setup.Api_datomic_async

class Scoped extends Test {
  Scoped(this, Api_datomic_async)
}
