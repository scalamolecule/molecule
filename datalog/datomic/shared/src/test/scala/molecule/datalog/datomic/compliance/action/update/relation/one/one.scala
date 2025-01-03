package molecule.datalog.datomic.compliance.action.update.relation.one

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.update.relation.one._
import molecule.datalog.datomic.setup.Api_datomic_async

class One_Map extends Test {
  One_Map(this, Api_datomic_async)
}
class One_Map_add extends Test {
  One_Map_add(this, Api_datomic_async)
}
class One_Map_remove extends Test {
  One_Map_remove(this, Api_datomic_async)
}
class One_One extends Test {
  One_One(this, Api_datomic_async)
}
class One_Seq extends Test {
  One_Seq(this, Api_datomic_async)
}
class One_Seq_add extends Test {
  One_Seq_add(this, Api_datomic_async)
}
class One_Seq_remove extends Test {
  One_Seq_remove(this, Api_datomic_async)
}
class One_Set extends Test {
  One_Set(this, Api_datomic_async)
}
class One_Set_add extends Test {
  One_Set_add(this, Api_datomic_async)
}
class One_Set_remove extends Test {
  One_Set_remove(this, Api_datomic_async)
}