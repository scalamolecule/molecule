package molecule.db.sql.postgres.compliance.api

import molecule.core.setup.MUnit_io
import molecule.db.compliance.test.api.IOApi
import molecule.db.sql.postgres.setup.Api_postgres_io

class IOApiTest extends MUnit_io {
  IOApi(this, Api_postgres_io)
}