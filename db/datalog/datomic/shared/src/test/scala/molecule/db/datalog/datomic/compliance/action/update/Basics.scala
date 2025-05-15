package molecule.db.datalog.datomic.compliance.action.update

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.action.update.Basics
import molecule.db.datalog.datomic.setup.Api_datomic_async

class BasicsTest extends Test {
  Basics(this, Api_datomic_async)
}
