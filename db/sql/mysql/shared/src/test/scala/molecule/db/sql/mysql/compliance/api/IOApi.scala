package molecule.db.sql.mysql.compliance.api

import molecule.core.setup.MUnit_io
import molecule.db.compliance.test.api.IOApi
import molecule.db.sql.mysql.setup.Api_mysql_io

class IOApiTest extends MUnit_io {
  IOApi(this, Api_mysql_io)
}