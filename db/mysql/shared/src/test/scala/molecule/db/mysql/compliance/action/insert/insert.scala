package molecule.db.mysql.compliance.action.insert

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db
import molecule.db.compliance.test.action.insert.*
import molecule.db.mysql.setup.Api_mysql_async

class InsertCardOneTest extends MUnit {
  InsertCardOne(this, Api_mysql_async)
}
class InsertCardSeqTest extends MUnit_arrays {
  InsertCardSeq(this, Api_mysql_async)
}
class InsertCardSetTest extends MUnit {
  InsertCardSet(this, Api_mysql_async)
}
class InsertCardMapTest extends MUnit {
  InsertCardMap(this, Api_mysql_async)
}
class InsertRefsTest extends MUnit {
  InsertRefs(this, Api_mysql_async)
}
class InsertSemanticsTest extends MUnit {
  InsertSemantics(this, Api_mysql_async)
}
