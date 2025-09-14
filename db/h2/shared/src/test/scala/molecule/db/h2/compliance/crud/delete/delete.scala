package molecule.db.h2.compliance.crud.delete

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.crud.delete.{Delete_filter, Delete_id}
import molecule.db.h2
import molecule.db.h2.setup.Api_h2_async

class Delete_idTest extends MUnit {
  Delete_id(this, Api_h2_async)
}
class Delete_filterTest extends MUnit {
  Delete_filter(this, Api_h2_async)
}
