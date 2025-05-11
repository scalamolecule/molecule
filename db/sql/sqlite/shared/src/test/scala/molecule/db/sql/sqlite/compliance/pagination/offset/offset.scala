package molecule.db.sql.sqlite.compliance.pagination.offset

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.pagination.offset.{OffsetBackwards, OffsetForward, OffsetSemantics}
import molecule.db.sql
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class OffsetBackwardsTest extends Test {
  OffsetBackwards(this, Api_sqlite_async)
}
class OffsetForwardTest extends Test {
  OffsetForward(this, Api_sqlite_async)
}
class OffsetSemanticsTest extends Test {
  OffsetSemantics(this, Api_sqlite_async)
}
