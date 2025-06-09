package molecule.db.sql.mysql.compliance.validation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.StringValidationFns
import molecule.db.sql.mysql.setup.Api_mysql_async

class StringValidationFnsTest extends MUnit {
  StringValidationFns(this, Api_mysql_async)
}
