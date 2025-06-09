package molecule.db.datalog.datomic.compliance.api

import molecule.core.setup.MUnit
import molecule.db.compliance.test.api.AsyncApi
import molecule.db.datalog.datomic.setup.Api_datomic_async

class AsyncApiTest extends MUnit {
  AsyncApi(this, Api_datomic_async)
}
