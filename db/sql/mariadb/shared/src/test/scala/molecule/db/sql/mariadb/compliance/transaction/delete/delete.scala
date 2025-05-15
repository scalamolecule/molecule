package molecule.db.sql.mariadb.compliance.transaction.delete

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.action.delete.{Delete_filter, Delete_id}
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class Delete_idTest extends Test {
  Delete_id(this, Api_mariadb_async)
}
class Delete_filterTest extends Test {
  Delete_filter(this, Api_mariadb_async)
}
