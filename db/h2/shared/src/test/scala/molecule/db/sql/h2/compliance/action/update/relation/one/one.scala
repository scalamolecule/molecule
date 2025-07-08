package molecule.db.sql.h2.compliance.action.update.relation.one

import molecule.core.setup.MUnit
import molecule.db.compliance.test.action.update.relation.one.*
import molecule.db.sql.h2.setup.Api_h2_async

class One_MapTest extends MUnit {
  One_Map(this, Api_h2_async)
}
class One_Map_addTest extends MUnit {
  One_Map_add(this, Api_h2_async)
}
class One_Map_removeTest extends MUnit {
  One_Map_remove(this, Api_h2_async)
}
class One_OneTest extends MUnit {
  One_One(this, Api_h2_async)
}
class One_SeqTest extends MUnit {
  One_Seq(this, Api_h2_async)
}
class One_Seq_addTest extends MUnit {
  One_Seq_add(this, Api_h2_async)
}
class One_Seq_removeTest extends MUnit {
  One_Seq_remove(this, Api_h2_async)
}
class One_SetTest extends MUnit {
  One_Set(this, Api_h2_async)
}
class One_Set_addTest extends MUnit {
  One_Set_add(this, Api_h2_async)
}
class One_Set_removeTest extends MUnit {
  One_Set_remove(this, Api_h2_async)
}
