package molecule.db.sql.mariadb.compliance.transaction.update.relation.one

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.action.update.relation.one.{One_Map, One_Map_add, One_Map_remove, One_One, One_Seq, One_Seq_add, One_Seq_remove, One_Set, One_Set_add, One_Set_remove}
import molecule.db.sql
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class One_MapTest extends Test {
  One_Map(this, Api_mariadb_async)
}
class One_Map_addTest extends Test {
  One_Map_add(this, Api_mariadb_async)
}
class One_Map_removeTest extends Test {
  One_Map_remove(this, Api_mariadb_async)
}
class One_OneTest extends Test {
  One_One(this, Api_mariadb_async)
}
class One_SeqTest extends Test {
  One_Seq(this, Api_mariadb_async)
}
class One_Seq_addTest extends Test {
  One_Seq_add(this, Api_mariadb_async)
}
class One_Seq_removeTest extends Test {
  One_Seq_remove(this, Api_mariadb_async)
}
class One_SetTest extends Test {
  One_Set(this, Api_mariadb_async)
}
class One_Set_addTest extends Test {
  One_Set_add(this, Api_mariadb_async)
}
class One_Set_removeTest extends Test {
  One_Set_remove(this, Api_mariadb_async)
}
