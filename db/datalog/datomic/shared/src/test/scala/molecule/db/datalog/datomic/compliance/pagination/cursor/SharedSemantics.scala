package molecule.db.datalog.datomic.compliance.pagination.cursor

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.pagination.SharedSemantics
import molecule.db.datalog
import molecule.db.datalog.datomic.setup.Api_datomic_async

class SharedSemanticsTest extends Test {
  SharedSemantics(this, Api_datomic_async)
}
