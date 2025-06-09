package molecule.db.sql.mariadb.compliance.validation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.RequiredAttrs
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class RequiredAttrsTest extends MUnit {
  RequiredAttrs(this, Api_mariadb_async)
}
