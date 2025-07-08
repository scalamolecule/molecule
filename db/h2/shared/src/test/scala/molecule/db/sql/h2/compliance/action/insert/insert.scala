package molecule.db.sql.h2.compliance.action.insert

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db.compliance.test.action.insert.*
import molecule.db.sql.h2.setup.Api_h2_async

class InsertCardOneTest extends MUnit {
  InsertCardOne(this, Api_h2_async)
}
class InsertCardSetTest extends MUnit {
  InsertCardSet(this, Api_h2_async)
}
class InsertCardMapTest extends MUnit {
  InsertCardMap(this, Api_h2_async)
}
class InsertCardSeqTest extends MUnit_arrays {
  InsertCardSeq(this, Api_h2_async)
}
class InsertRefsTest extends MUnit {
  InsertRefs(this, Api_h2_async)
}
class InsertSemanticsTest extends MUnit {
  InsertSemantics(this, Api_h2_async)
}
