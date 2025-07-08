package molecule.db.mariadb.compliance.segments

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.segments.Prefixed
import molecule.db.mariadb.setup.Api_mariadb_async

class PrefixedTest extends MUnit {
  Prefixed(this, Api_mariadb_async)
}
