package molecule.db.mysql.compliance.api

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.api.AsyncApi
import molecule.db.mysql.setup.Api_mysql_async

class AsyncApiTest extends MUnit {
  AsyncApi(this, Api_mysql_async)
}
