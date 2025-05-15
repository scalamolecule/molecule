package molecule.db.sql.mariadb.compliance.pagination.offset

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.pagination.offset.{OffsetBackwards, OffsetForward, OffsetSemantics}
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class OffsetBackwardsTest extends Test {
  OffsetBackwards(this, Api_mariadb_async)
}
class OffsetForwardTest extends Test {
  OffsetForward(this, Api_mariadb_async)
}
class OffsetSemanticsTest extends Test {
  OffsetSemantics(this, Api_mariadb_async)
}
