package molecule.db.sql.postgres.compliance.action.insert

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db.compliance.test.action.insert.*
import molecule.db.sql.postgres.setup.Api_postgres_async

class InsertCardOneTest extends MUnit {
  InsertCardOne(this, Api_postgres_async)
}
class InsertCardSeqTest extends MUnit_arrays {
  InsertCardSeq(this, Api_postgres_async)
}
class InsertCardSetTest extends MUnit {
  InsertCardSet(this, Api_postgres_async)
}
class InsertCardMapTest extends MUnit {
  InsertCardMap(this, Api_postgres_async)
}
class InsertRefsTest extends MUnit {
  InsertRefs(this, Api_postgres_async)
}
class InsertSemanticsTest extends MUnit {
  InsertSemantics(this, Api_postgres_async)
}
