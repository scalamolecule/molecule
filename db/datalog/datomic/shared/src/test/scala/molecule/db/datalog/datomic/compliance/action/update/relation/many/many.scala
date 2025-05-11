package molecule.db.datalog.datomic.compliance.action.update.relation.many

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.action.update.relation.many.{Many_Map, Many_Map_add, Many_Map_remove, Many_One, Many_Seq, Many_Seq_add, Many_Seq_remove, Many_Set, Many_Set_add, Many_Set_remove}
import molecule.db.datalog
import molecule.db.datalog.datomic.setup.Api_datomic_async

class Many_MapTest extends Test {
  Many_Map(this, Api_datomic_async)
}
class Many_Map_addTest extends Test {
  Many_Map_add(this, Api_datomic_async)
}
class Many_Map_removeTest extends Test {
  Many_Map_remove(this, Api_datomic_async)
}
class Many_OneTest extends Test {
  Many_One(this, Api_datomic_async)
}
class Many_SeqTest extends Test {
  Many_Seq(this, Api_datomic_async)
}
class Many_Seq_addTest extends Test {
  Many_Seq_add(this, Api_datomic_async)
}
class Many_Seq_removeTest extends Test {
  Many_Seq_remove(this, Api_datomic_async)
}
class Many_SetTest extends Test {
  Many_Set(this, Api_datomic_async)
}
class Many_Set_addTest extends Test {
  Many_Set_add(this, Api_datomic_async)
}
class Many_Set_removeTest extends Test {
  Many_Set_remove(this, Api_datomic_async)
}
