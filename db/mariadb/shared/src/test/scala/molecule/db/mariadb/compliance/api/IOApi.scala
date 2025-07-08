package molecule.db.mariadb.compliance.api

import molecule.core.setup.MUnit_io
import molecule.db
import molecule.db.compliance.test.api.IOApi
import molecule.db.mariadb.setup.Api_mariadb_io

class IOApiTest extends MUnit_io {
  IOApi(this, Api_mariadb_io)
}