package molecule.db.postgresql.compliance.api

import molecule.core.setup.MUnit_io
import molecule.db
import molecule.db.compliance.test.api.IOApi
import molecule.db.postgresql.setup.Api_postgresql_io

class IOApiTest extends MUnit_io {
  IOApi(this, Api_postgresql_io)
}