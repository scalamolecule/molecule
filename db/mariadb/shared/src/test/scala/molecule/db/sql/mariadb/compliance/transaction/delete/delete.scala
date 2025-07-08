package molecule.db.sql.mariadb.compliance.transaction.delete

import molecule.core.setup.MUnit
import molecule.db.compliance.test.action.delete.{Delete_filter, Delete_id}
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class Delete_idTest extends MUnit {
  Delete_id(this, Api_mariadb_async)
}
class Delete_filterTest extends MUnit {
  Delete_filter(this, Api_mariadb_async)
}
