package molecule.db.sql.mariadb.compliance.api

import molecule.db.compliance.setup.Test_io
import molecule.db.compliance.test.api.IOApi
import molecule.db.sql
import molecule.db.sql.mariadb.setup.Api_mariadb_io

class IOApiTest extends Test_io {
  IOApi(this, Api_mariadb_io)
}