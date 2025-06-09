package molecule.db.sql.h2.compliance.api

import molecule.core.setup.MUnit_io
import molecule.db.compliance.test.api.IOApi
import molecule.db.sql.h2.setup.Api_h2_io

class IOApiTest extends MUnit_io {
  IOApi(this, Api_h2_io)
}
