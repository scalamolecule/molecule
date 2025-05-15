package molecule.db.sql.postgres.compliance.api

import molecule.db.compliance.setup.Test_io
import molecule.db.compliance.test.api.IOApi
import molecule.db.sql.postgres.setup.Api_postgres_io

class IOApiTest extends Test_io {
  IOApi(this, Api_postgres_io)
}