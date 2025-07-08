package molecule.db.mariadb.compliance.pagination.cursor

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.pagination.SharedSemantics
import molecule.db.mariadb.setup.Api_mariadb_async

class SharedSemanticsTest extends MUnit {
  SharedSemantics(this, Api_mariadb_async)
}
