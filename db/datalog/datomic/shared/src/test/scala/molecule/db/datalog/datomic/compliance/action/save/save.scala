package molecule.db.datalog.datomic.compliance.action.save

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db.compliance.test.action.save.*
import molecule.db.datalog.datomic.setup.Api_datomic_async

class SaveCardOneTest extends MUnit {
  SaveCardOne(this, Api_datomic_async)
}
class SaveCardSeqTest extends MUnit_arrays {
  SaveCardSeq(this, Api_datomic_async)
}
class SaveCardSetTest extends MUnit {
  SaveCardSet(this, Api_datomic_async)
}
class SaveCardMapTest extends MUnit {
  SaveCardMap(this, Api_datomic_async)
}
class SaveRefsTest extends MUnit {
  SaveRefs(this, Api_datomic_async)
}
class SaveSemanticsTest extends MUnit {
  SaveSemantics(this, Api_datomic_async)
}
