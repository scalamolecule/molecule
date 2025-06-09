package molecule.db.sql.mariadb.compliance.pagination.cursor

import molecule.core.setup.MUnit
import molecule.db.compliance.test.pagination.SharedSemantics
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class SharedSemanticsTest extends MUnit {
  SharedSemantics(this, Api_mariadb_async)
}
