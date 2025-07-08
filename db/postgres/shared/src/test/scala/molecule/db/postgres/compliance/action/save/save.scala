package molecule.db.postgres.compliance.action.save

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db
import molecule.db.compliance.test.action.save.*
import molecule.db.postgres.setup.Api_postgres_async

class SaveCardOneTest extends MUnit {
  SaveCardOne(this, Api_postgres_async)
}
class SaveCardSeqTest extends MUnit_arrays {
  SaveCardSeq(this, Api_postgres_async)
}
class SaveCardSetTest extends MUnit {
  SaveCardSet(this, Api_postgres_async)
}
class SaveCardMapTest extends MUnit {
  SaveCardMap(this, Api_postgres_async)
}
class SaveRefsTest extends MUnit {
  SaveRefs(this, Api_postgres_async)
}
class SaveSemanticsTest extends MUnit {
  SaveSemantics(this, Api_postgres_async)
}
