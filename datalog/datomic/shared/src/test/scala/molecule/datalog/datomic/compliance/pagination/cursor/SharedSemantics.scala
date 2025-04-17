package molecule.datalog.datomic.compliance.pagination.cursor

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.cursor._
import molecule.datalog.datomic.setup.Api_datomic_async

class SharedSemanticsTest extends Test {
  SharedSemantics(this, Api_datomic_async)
}
