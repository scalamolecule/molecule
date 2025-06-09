package molecule.db.sql.mysql.compliance.validation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.MandatoryAttrs
import molecule.db.sql.mysql.setup.Api_mysql_async

class MandatoryAttrsTest extends MUnit {
  MandatoryAttrs(this, Api_mysql_async)
}
