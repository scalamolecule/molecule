package molecule.db.datalog.datomic.compliance.pagination.cursor

import molecule.core.setup.MUnit
import molecule.db.compliance.test.pagination.SharedSemantics
import molecule.db.datalog.datomic.setup.Api_datomic_async

class SharedSemanticsTest extends MUnit {
  SharedSemantics(this, Api_datomic_async)
}
