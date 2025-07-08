package molecule.db.sql.postgres.compliance.action.delete

import molecule.core.setup.MUnit
import molecule.db.compliance.test.action.delete.{Delete_filter, Delete_id}
import molecule.db.sql.postgres.setup.Api_postgres_async

class Delete_idTest extends MUnit {
  Delete_id(this, Api_postgres_async)
}
class Delete_filterTest extends MUnit {
  Delete_filter(this, Api_postgres_async)
}
