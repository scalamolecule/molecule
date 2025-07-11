package molecule.db.postgresql.compliance.action.insert

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db
import molecule.db.compliance.test.action.insert.*
import molecule.db.postgresql
import molecule.db.postgresql.setup.Api_postgresql_async

class InsertCardOneTest extends MUnit {
  InsertCardOne(this, Api_postgresql_async)
}
class InsertCardSeqTest extends MUnit_arrays {
  InsertCardSeq(this, Api_postgresql_async)
}
class InsertCardSetTest extends MUnit {
  InsertCardSet(this, Api_postgresql_async)
}
class InsertCardMapTest extends MUnit {
  InsertCardMap(this, Api_postgresql_async)
}
class InsertRefsTest extends MUnit {
  InsertRefs(this, Api_postgresql_async)
}
class InsertSemanticsTest extends MUnit {
  InsertSemantics(this, Api_postgresql_async)
}
