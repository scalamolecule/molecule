package molecule.db.datalog.datomic.compliance.api

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.api.AsyncApi
import molecule.db.datalog.datomic.setup.Api_datomic_async

class AsyncApiTest extends Test {
  AsyncApi(this, Api_datomic_async)
}
