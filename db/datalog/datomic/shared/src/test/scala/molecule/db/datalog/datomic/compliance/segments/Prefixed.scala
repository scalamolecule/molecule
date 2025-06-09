package molecule.db.datalog.datomic.compliance.segments

import molecule.core.setup.MUnit
import molecule.db.compliance.test.segments.Prefixed
import molecule.db.datalog.datomic.setup.Api_datomic_async

class PrefixedTest extends MUnit {
  Prefixed(this, Api_datomic_async)
}
