package molecule.db.mariadb.compliance.transaction.update.relation.many

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.action.update.relation.many.*
import molecule.db.mariadb.setup.Api_mariadb_async

class Many_MapTest extends MUnit {
  Many_Map(this, Api_mariadb_async)
}
class Many_Map_addTest extends MUnit {
  Many_Map_add(this, Api_mariadb_async)
}
class Many_Map_removeTest extends MUnit {
  Many_Map_remove(this, Api_mariadb_async)
}
class Many_OneTest extends MUnit {
  Many_One(this, Api_mariadb_async)
}
class Many_SeqTest extends MUnit {
  Many_Seq(this, Api_mariadb_async)
}
class Many_Seq_addTest extends MUnit {
  Many_Seq_add(this, Api_mariadb_async)
}
class Many_Seq_removeTest extends MUnit {
  Many_Seq_remove(this, Api_mariadb_async)
}
class Many_SetTest extends MUnit {
  Many_Set(this, Api_mariadb_async)
}
class Many_Set_addTest extends MUnit {
  Many_Set_add(this, Api_mariadb_async)
}
class Many_Set_removeTest extends MUnit {
  Many_Set_remove(this, Api_mariadb_async)
}
