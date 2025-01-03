package molecule.sql.h2.compliance.action.update.relation.many

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.update.relation.many._
import molecule.sql.h2.setup.Api_h2_async

class Many_Map extends Test {
  Many_Map(this, Api_h2_async)
}
class Many_Map_add extends Test {
  Many_Map_add(this, Api_h2_async)
}
class Many_Map_remove extends Test {
  Many_Map_remove(this, Api_h2_async)
}
class Many_One extends Test {
  Many_One(this, Api_h2_async)
}
class Many_Seq extends Test {
  Many_Seq(this, Api_h2_async)
}
class Many_Seq_add extends Test {
  Many_Seq_add(this, Api_h2_async)
}
class Many_Seq_remove extends Test {
  Many_Seq_remove(this, Api_h2_async)
}
class Many_Set extends Test {
  Many_Set(this, Api_h2_async)
}
class Many_Set_add extends Test {
  Many_Set_add(this, Api_h2_async)
}
class Many_Set_remove extends Test {
  Many_Set_remove(this, Api_h2_async)
}