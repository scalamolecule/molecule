package molecule.db.sql.mariadb.compliance.api

import molecule.coreTests.setup.Test_io
import molecule.coreTests.spi.api.*
import molecule.db.sql
import molecule.db.sql.mariadb.setup.Api_mariadb_io

class IOApiTest extends Test_io {
  IOApi(this, Api_mariadb_io)
}