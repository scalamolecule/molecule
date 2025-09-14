package molecule.db.mysql.compliance.crud.save

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db
import molecule.db.compliance.test.crud.save.*
import molecule.db.mysql.setup.Api_mysql_async

class SaveOneValueTest extends MUnit {
  SaveOneValue(this, Api_mysql_async)
}
class SaveSeqValueTest extends MUnit_arrays {
  SaveSeqValue(this, Api_mysql_async)
}
class SaveSetValueTest extends MUnit {
  SaveSetValue(this, Api_mysql_async)
}
class SaveMapValueTest extends MUnit {
  SaveMapValue(this, Api_mysql_async)
}
class SaveRefsTest extends MUnit {
  SaveRefs(this, Api_mysql_async)
}
class SaveSemanticsTest extends MUnit {
  SaveSemantics(this, Api_mysql_async)
}
