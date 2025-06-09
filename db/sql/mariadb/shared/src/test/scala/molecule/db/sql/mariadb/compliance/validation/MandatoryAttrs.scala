package molecule.db.sql.mariadb.compliance.validation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.MandatoryAttrs
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class MandatoryAttrsTest extends MUnit {
  MandatoryAttrs(this, Api_mariadb_async)
}
