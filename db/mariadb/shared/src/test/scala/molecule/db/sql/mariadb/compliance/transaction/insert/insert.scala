package molecule.db.sql.mariadb.compliance.transaction.insert

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db.compliance.test.action.insert.*
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class InsertCardOneTest extends MUnit {
  InsertCardOne(this, Api_mariadb_async)
}
class InsertCardSeqTest extends MUnit_arrays {
  InsertCardSeq(this, Api_mariadb_async)
}
class InsertCardSetTest extends MUnit {
  InsertCardSet(this, Api_mariadb_async)
}
class InsertCardMapTest extends MUnit {
  InsertCardMap(this, Api_mariadb_async)
}
class InsertRefsTest extends MUnit {
  InsertRefs(this, Api_mariadb_async)
}
class InsertSemanticsTest extends MUnit {
  InsertSemantics(this, Api_mariadb_async)
}
