package molecule.db.sql.mysql.compliance.validation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.Enumerations
import molecule.db.sql.mysql.setup.Api_mysql_async

class EnumerationsTest extends MUnit {
  Enumerations(this, Api_mysql_async)
}
