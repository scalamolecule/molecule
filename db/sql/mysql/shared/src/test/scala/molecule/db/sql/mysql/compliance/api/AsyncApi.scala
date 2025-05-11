package molecule.db.sql.mysql.compliance.api

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.api.AsyncApi
import molecule.db.sql
import molecule.db.sql.mysql.setup.Api_mysql_async

class AsyncApiTest extends Test {
  AsyncApi(this, Api_mysql_async)
}
