package molecule.sql.mariadb.compliance.pagination.offset

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.offset._
import molecule.sql.mariadb.setup.Api_mariadb_async

class OffsetBackwardsTest extends Test {
  OffsetBackwards(this, Api_mariadb_async)
}
class OffsetForwardTest extends Test {
  OffsetForward(this, Api_mariadb_async)
}
class OffsetSemanticsTest extends Test {
  OffsetSemantics(this, Api_mariadb_async)
}
