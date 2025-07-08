package molecule.db.postgres.compliance.api

import molecule.core.setup.MUnit_io
import molecule.db
import molecule.db.compliance.test.api.IOApi
import molecule.db.postgres.setup.Api_postgres_io

class IOApiTest extends MUnit_io {
  IOApi(this, Api_postgres_io)
}