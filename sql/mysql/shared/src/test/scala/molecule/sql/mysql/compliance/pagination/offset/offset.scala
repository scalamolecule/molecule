package molecule.sql.mysql.compliance.pagination.offset

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.offset._
import molecule.sql.mysql.setup.Api_mysql_async

class OffsetBackwards extends Test {
  OffsetBackwards(this, Api_mysql_async)
}
class OffsetForward extends Test {
  OffsetForward(this, Api_mysql_async)
}
class OffsetSemantics extends Test {
  OffsetSemantics(this, Api_mysql_async)
}
