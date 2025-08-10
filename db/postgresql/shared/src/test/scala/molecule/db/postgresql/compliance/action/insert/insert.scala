package molecule.db.postgresql.compliance.action.insert

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db
import molecule.db.compliance.test.action.insert.*
import molecule.db.postgresql
import molecule.db.postgresql.setup.Api_postgresql_async

class InsertOneValueTest extends MUnit {
  InsertOneValue(this, Api_postgresql_async)
}
class InsertSeqValueTest extends MUnit_arrays {
  InsertSeqValue(this, Api_postgresql_async)
}
class InsertSetValueTest extends MUnit {
  InsertSetValue(this, Api_postgresql_async)
}
class InsertMapValueTest extends MUnit {
  InsertMapValue(this, Api_postgresql_async)
}
class InsertRefsTest extends MUnit {
  InsertRefs(this, Api_postgresql_async)
}
class InsertSemanticsTest extends MUnit {
  InsertSemantics(this, Api_postgresql_async)
}
