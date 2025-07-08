package molecule.db.mysql.compliance.pagination.offset

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.pagination.offset.{OffsetBackwards, OffsetForward, OffsetSemantics}
import molecule.db.mysql.setup.Api_mysql_async

class OffsetBackwardsTest extends MUnit {
  OffsetBackwards(this, Api_mysql_async)
}
class OffsetForwardTest extends MUnit {
  OffsetForward(this, Api_mysql_async)
}
class OffsetSemanticsTest extends MUnit {
  OffsetSemantics(this, Api_mysql_async)
}
