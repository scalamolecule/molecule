package molecule.db.sql.mariadb.compliance.validation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.Enumerations
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class EnumerationsTest extends MUnit {
  Enumerations(this, Api_mariadb_async)
}
