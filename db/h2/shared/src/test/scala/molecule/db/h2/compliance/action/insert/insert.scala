package molecule.db.h2.compliance.action.insert

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db
import molecule.db.compliance.test.action.insert.*
import molecule.db.h2
import molecule.db.h2.setup.Api_h2_async

class InsertOneValueTest extends MUnit {
  InsertOneValue(this, Api_h2_async)
}
class InsertSetValueTest extends MUnit {
  InsertSetValue(this, Api_h2_async)
}
class InsertMapValueTest extends MUnit {
  InsertMapValue(this, Api_h2_async)
}
class InsertSeqValueTest extends MUnit_arrays {
  InsertSeqValue(this, Api_h2_async)
}
class InsertRefsTest extends MUnit {
  InsertRefs(this, Api_h2_async)
}
class InsertSemanticsTest extends MUnit {
  InsertSemantics(this, Api_h2_async)
}
