package molecule.sql.mariadb.compliance.pagination.offset

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.pagination.offset._
import molecule.sql.mariadb.setup.Api_mariadb_async

class OffsetBackwards extends MUnitSuite {
  OffsetBackwards(this, Api_mariadb_async)
}
class OffsetForward extends MUnitSuite {
  OffsetForward(this, Api_mariadb_async)
}
class OffsetSemantics extends MUnitSuite {
  OffsetSemantics(this, Api_mariadb_async)
}
