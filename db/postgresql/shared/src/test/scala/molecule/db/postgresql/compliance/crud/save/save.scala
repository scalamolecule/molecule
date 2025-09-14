package molecule.db.postgresql.compliance.crud.save

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db
import molecule.db.compliance.test.crud.save.*
import molecule.db.postgresql.setup.Api_postgresql_async

class SaveOneValueTest extends MUnit {
  SaveOneValue(this, Api_postgresql_async)
}
class SaveSeqValueTest extends MUnit_arrays {
  SaveSeqValue(this, Api_postgresql_async)
}
class SaveSetValueTest extends MUnit {
  SaveSetValue(this, Api_postgresql_async)
}
class SaveMapValueTest extends MUnit {
  SaveMapValue(this, Api_postgresql_async)
}
class SaveRefsTest extends MUnit {
  SaveRefs(this, Api_postgresql_async)
}
class SaveSemanticsTest extends MUnit {
  SaveSemantics(this, Api_postgresql_async)
}
