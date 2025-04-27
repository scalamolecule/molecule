package molecule.db.sql.h2.compliance.action.update.relation.many

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.update.relation.many.*
import molecule.db.sql
import molecule.db.sql.h2.setup.Api_h2_async

class Many_MapTest extends Test {
  Many_Map(this, Api_h2_async)
}
class Many_Map_addTest extends Test {
  Many_Map_add(this, Api_h2_async)
}
class Many_Map_removeTest extends Test {
  Many_Map_remove(this, Api_h2_async)
}
class Many_OneTest extends Test {
  Many_One(this, Api_h2_async)
}
class Many_SeqTest extends Test {
  Many_Seq(this, Api_h2_async)
}
class Many_Seq_addTest extends Test {
  Many_Seq_add(this, Api_h2_async)
}
class Many_Seq_removeTest extends Test {
  Many_Seq_remove(this, Api_h2_async)
}
class Many_SetTest extends Test {
  Many_Set(this, Api_h2_async)
}
class Many_Set_addTest extends Test {
  Many_Set_add(this, Api_h2_async)
}
class Many_Set_removeTest extends Test {
  Many_Set_remove(this, Api_h2_async)
}
