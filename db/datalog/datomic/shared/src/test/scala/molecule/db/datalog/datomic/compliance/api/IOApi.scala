package molecule.db.datalog.datomic.compliance.api

import molecule.db.compliance.setup.Test_io
import molecule.db.compliance.test.api.IOApi
import molecule.db.datalog.datomic.setup.Api_datomic_io

class IOApiTest extends Test_io {
  IOApi(this, Api_datomic_io)
}
