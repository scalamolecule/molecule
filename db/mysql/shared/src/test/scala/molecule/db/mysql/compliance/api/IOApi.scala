package molecule.db.mysql.compliance.api

import molecule.core.setup.MUnit_io
import molecule.db
import molecule.db.compliance.test.api.IOApi
import molecule.db.mysql.setup.Api_mysql_io

class IOApiTest extends MUnit_io {
  IOApi(this, Api_mysql_io)
}