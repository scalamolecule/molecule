package molecule.db.sql.h2.compliance.action.save

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db.compliance.test.action.save.*
import molecule.db.sql.h2.setup.Api_h2_async

class SaveCardOneTest extends MUnit {
  SaveCardOne(this, Api_h2_async)
}
class SaveCardSeqTest extends MUnit_arrays {
  SaveCardSeq(this, Api_h2_async)
}
class SaveCardSetTest extends MUnit {
  SaveCardSet(this, Api_h2_async)
}
class SaveCardMapTest extends MUnit {
  SaveCardMap(this, Api_h2_async)
}
class SaveRefsTest extends MUnit {
  SaveRefs(this, Api_h2_async)
}
class SaveSemanticsTest extends MUnit {
  SaveSemantics(this, Api_h2_async)
}
