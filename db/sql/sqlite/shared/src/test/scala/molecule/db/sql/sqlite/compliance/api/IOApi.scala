package molecule.db.sql.sqlite.compliance.api

import molecule.db.compliance.setup.Test_io
import molecule.db.compliance.test.api.IOApi
import molecule.db.sql
import molecule.db.sql.sqlite.setup.Api_sqlite_io

class IOApiTest extends Test_io {
  IOApi(this, Api_sqlite_io)
}
