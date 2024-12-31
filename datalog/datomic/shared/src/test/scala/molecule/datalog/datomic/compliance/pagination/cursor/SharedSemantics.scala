package molecule.datalog.datomic.compliance.pagination.cursor

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.pagination.cursor.SharedSemantics
import molecule.datalog.datomic.setup.Api_datomic_async

class SharedSemantics extends MUnitSuite {
  SharedSemantics(this, Api_datomic_async)
}
