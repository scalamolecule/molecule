package molecule.db.sql.h2.compliance.action.delete

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.action.delete.{Delete_filter, Delete_id}
import molecule.db.sql.h2.setup.Api_h2_async

class Delete_idTest extends Test {
  Delete_id(this, Api_h2_async)
}
class Delete_filterTest extends Test {
  Delete_filter(this, Api_h2_async)
}
