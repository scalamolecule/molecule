package molecule.db.sql.postgres.compliance.api

import molecule.coreTests.setup.Test_io
import molecule.coreTests.spi.api.*
import molecule.db.sql
import molecule.db.sql.postgres.setup.Api_postgres_io

class IOApiTest extends Test_io {
  IOApi(this, Api_postgres_io)
}