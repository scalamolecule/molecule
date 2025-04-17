package molecule.datalog.datomic.compliance.api

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.api._
import molecule.datalog.datomic.setup.Api_datomic_async

class AsyncApiTest extends Test {
  AsyncApi(this, Api_datomic_async)
}
