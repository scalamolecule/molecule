package molecule.db.postgresql.compliance.action.save

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db
import molecule.db.compliance.test.action.save.*
import molecule.db.postgresql.setup.Api_postgresql_async

class SaveCardOneTest extends MUnit {
  SaveCardOne(this, Api_postgresql_async)
}
class SaveCardSeqTest extends MUnit_arrays {
  SaveCardSeq(this, Api_postgresql_async)
}
class SaveCardSetTest extends MUnit {
  SaveCardSet(this, Api_postgresql_async)
}
class SaveCardMapTest extends MUnit {
  SaveCardMap(this, Api_postgresql_async)
}
class SaveRefsTest extends MUnit {
  SaveRefs(this, Api_postgresql_async)
}
class SaveSemanticsTest extends MUnit {
  SaveSemantics(this, Api_postgresql_async)
}
