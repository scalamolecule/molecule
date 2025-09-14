package molecule.db.h2.compliance.crud.save

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db
import molecule.db.compliance.test.crud.save.*
import molecule.db.h2.setup.Api_h2_async

class SaveOneValueTest extends MUnit {
  SaveOneValue(this, Api_h2_async)
}
class SaveSeqValueTest extends MUnit_arrays {
  SaveSeqValue(this, Api_h2_async)
}
class SaveSetValueTest extends MUnit {
  SaveSetValue(this, Api_h2_async)
}
class SaveMapValueTest extends MUnit {
  SaveMapValue(this, Api_h2_async)
}
class SaveRefsTest extends MUnit {
  SaveRefs(this, Api_h2_async)
}
class SaveSemanticsTest extends MUnit {
  SaveSemantics(this, Api_h2_async)
}
