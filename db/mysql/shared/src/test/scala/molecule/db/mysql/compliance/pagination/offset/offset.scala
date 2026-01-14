package molecule.db.mysql.compliance.pagination.offset

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.pagination.offset.{Offset, OffsetSemantics}
import molecule.db.mysql.setup.Api_mysql_async

class OffsetTest extends MUnit {
  Offset(this, Api_mysql_async)
}
class OffsetSemanticsTest extends MUnit {
  OffsetSemantics(this, Api_mysql_async)
}
