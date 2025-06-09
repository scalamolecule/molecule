package molecule.db.datalog.datomic.compliance.action.delete

import molecule.core.setup.MUnit
import molecule.db.compliance.test.action.delete.{Delete_filter, Delete_id}
import molecule.db.datalog.datomic.setup.Api_datomic_async

class Delete_idTest extends MUnit {
  Delete_id(this, Api_datomic_async)
}
class Delete_filterTest extends MUnit {
  Delete_filter(this, Api_datomic_async)
}
