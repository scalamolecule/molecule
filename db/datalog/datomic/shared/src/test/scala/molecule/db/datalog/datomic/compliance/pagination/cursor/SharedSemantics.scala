package molecule.db.datalog.datomic.compliance.pagination.cursor

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.cursor.*
import molecule.db.datalog
import molecule.db.datalog.datomic.setup.Api_datomic_async

class SharedSemanticsTest extends Test {
  SharedSemantics(this, Api_datomic_async)
}
