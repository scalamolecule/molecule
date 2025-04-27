package molecule.db.datalog.datomic.compliance.api

import molecule.coreTests.setup.Test_io
import molecule.coreTests.spi.api.*
import molecule.db.datalog
import molecule.db.datalog.datomic.setup.Api_datomic_io

class IOApiTest extends Test_io {
  IOApi(this, Api_datomic_io)
}
