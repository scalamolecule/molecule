package molecule.datalog.datomic.compliance.api

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.api._
import molecule.datalog.datomic.setup.Api_datomic_async

class AsyncApi extends MUnitSuite {
  AsyncApi(this, Api_datomic_async)
}
