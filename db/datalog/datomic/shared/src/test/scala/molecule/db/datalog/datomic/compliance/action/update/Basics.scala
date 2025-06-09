package molecule.db.datalog.datomic.compliance.action.update

import molecule.core.setup.MUnit
import molecule.db.compliance.test.action.update.Basics
import molecule.db.datalog.datomic.setup.Api_datomic_async

class BasicsTest extends MUnit {
  Basics(this, Api_datomic_async)
}
