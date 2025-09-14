package molecule.db.sqlite.compliance.crud.insert

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db
import molecule.db.compliance.test.crud.insert.*
import molecule.db.sqlite
import molecule.db.sqlite.setup.Api_sqlite_async

class InsertOneValueTest extends MUnit {
  InsertOneValue(this, Api_sqlite_async)
}
class InsertSetValueTest extends MUnit {
  InsertSetValue(this, Api_sqlite_async)
}
class InsertMapValueTest extends MUnit {
  InsertMapValue(this, Api_sqlite_async)
}
class InsertSeqValueTest extends MUnit_arrays {
  InsertSeqValue(this, Api_sqlite_async)
}
class InsertRefsTest extends MUnit {
  InsertRefs(this, Api_sqlite_async)
}
class InsertSemanticsTest extends MUnit {
  InsertSemantics(this, Api_sqlite_async)
}
