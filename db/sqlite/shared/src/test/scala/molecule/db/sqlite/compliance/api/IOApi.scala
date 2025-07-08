package molecule.db.sqlite.compliance.api

import molecule.core.setup.MUnit_io
import molecule.db
import molecule.db.compliance.test.api.IOApi
import molecule.db.sqlite.setup.Api_sqlite_io

class IOApiTest extends MUnit_io {
  IOApi(this, Api_sqlite_io)
}
