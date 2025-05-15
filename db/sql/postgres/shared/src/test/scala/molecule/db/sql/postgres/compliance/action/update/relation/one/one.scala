package molecule.db.sql.postgres.compliance.action.update.relation.one

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.action.update.relation.one.*
import molecule.db.sql.postgres.setup.Api_postgres_async

class One_MapTest extends Test {
  One_Map(this, Api_postgres_async)
}
class One_Map_addTest extends Test {
  One_Map_add(this, Api_postgres_async)
}
class One_Map_removeTest extends Test {
  One_Map_remove(this, Api_postgres_async)
}
class One_OneTest extends Test {
  One_One(this, Api_postgres_async)
}
class One_SeqTest extends Test {
  One_Seq(this, Api_postgres_async)
}
class One_Seq_addTest extends Test {
  One_Seq_add(this, Api_postgres_async)
}
class One_Seq_removeTest extends Test {
  One_Seq_remove(this, Api_postgres_async)
}
class One_SetTest extends Test {
  One_Set(this, Api_postgres_async)
}
class One_Set_addTest extends Test {
  One_Set_add(this, Api_postgres_async)
}
class One_Set_removeTest extends Test {
  One_Set_remove(this, Api_postgres_async)
}
