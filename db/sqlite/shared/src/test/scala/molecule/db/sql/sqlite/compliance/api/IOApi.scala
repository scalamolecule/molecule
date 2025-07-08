package molecule.db.sql.sqlite.compliance.api

import molecule.core.setup.MUnit_io
import molecule.db.compliance.test.api.IOApi
import molecule.db.sql.sqlite.setup.Api_sqlite_io

class IOApiTest extends MUnit_io {
  IOApi(this, Api_sqlite_io)
}
