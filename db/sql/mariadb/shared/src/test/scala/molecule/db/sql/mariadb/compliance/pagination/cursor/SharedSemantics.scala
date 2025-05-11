package molecule.db.sql.mariadb.compliance.pagination.cursor

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.pagination.SharedSemantics
import molecule.db.sql
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class SharedSemanticsTest extends Test {
  SharedSemantics(this, Api_mariadb_async)
}
