package molecule.db.sql.mysql.compliance.api

import molecule.db.compliance.setup.Test_io
import molecule.db.compliance.test.api.IOApi
import molecule.db.sql.mysql.setup.Api_mysql_io

class IOApiTest extends Test_io {
  IOApi(this, Api_mysql_io)
}