package molecule.db.sql.mysql.compliance.validation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.RequiredAttrs
import molecule.db.sql.mysql.setup.Api_mysql_async

class RequiredAttrsTest extends MUnit {
  RequiredAttrs(this, Api_mysql_async)
}
