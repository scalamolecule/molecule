package molecule.db.sql.mariadb.compliance.transaction.save

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db.compliance.test.action.save.*
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class SaveCardOneTest extends MUnit {
  SaveCardOne(this, Api_mariadb_async)
}
class SaveCardSeqTest extends MUnit_arrays {
  SaveCardSeq(this, Api_mariadb_async)
}
class SaveCardSetTest extends MUnit {
  SaveCardSet(this, Api_mariadb_async)
}
class SaveCardMapTest extends MUnit {
  SaveCardMap(this, Api_mariadb_async)
}
class SaveRefsTest extends MUnit {
  SaveRefs(this, Api_mariadb_async)
}
class SaveSemanticsTest extends MUnit {
  SaveSemantics(this, Api_mariadb_async)
}
