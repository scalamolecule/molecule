package molecule.db.sql.h2.compliance.api

import molecule.db.compliance.setup.Test_io
import molecule.db.compliance.test.api.IOApi
import molecule.db.sql.h2.setup.Api_h2_io

class IOApiTest extends Test_io {
  IOApi(this, Api_h2_io)
}
