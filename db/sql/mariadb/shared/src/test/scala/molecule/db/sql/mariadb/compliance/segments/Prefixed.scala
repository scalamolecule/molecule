package molecule.db.sql.mariadb.compliance.segments

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.segments.Prefixed
import molecule.db.sql
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class PrefixedTest extends Test {
  Prefixed(this, Api_mariadb_async)
}
