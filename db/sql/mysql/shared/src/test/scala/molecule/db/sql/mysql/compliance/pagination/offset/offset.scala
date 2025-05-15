package molecule.db.sql.mysql.compliance.pagination.offset

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.pagination.offset.{OffsetBackwards, OffsetForward, OffsetSemantics}
import molecule.db.sql.mysql.setup.Api_mysql_async

class OffsetBackwardsTest extends Test {
  OffsetBackwards(this, Api_mysql_async)
}
class OffsetForwardTest extends Test {
  OffsetForward(this, Api_mysql_async)
}
class OffsetSemanticsTest extends Test {
  OffsetSemantics(this, Api_mysql_async)
}
