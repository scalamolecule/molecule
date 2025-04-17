package molecule.datalog.datomic.compliance.api

import molecule.coreTests.setup.Test_io
import molecule.coreTests.spi.api._
import molecule.datalog.datomic.setup.Api_datomic_io

class IOApiTest extends Test_io {
  IOApi(this, Api_datomic_io)
}
