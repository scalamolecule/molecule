package molecule.db.sql.mariadb.compliance.pagination.offset

import molecule.core.setup.MUnit
import molecule.db.compliance.test.pagination.offset.{OffsetBackwards, OffsetForward, OffsetSemantics}
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class OffsetBackwardsTest extends MUnit {
  OffsetBackwards(this, Api_mariadb_async)
}
class OffsetForwardTest extends MUnit {
  OffsetForward(this, Api_mariadb_async)
}
class OffsetSemanticsTest extends MUnit {
  OffsetSemantics(this, Api_mariadb_async)
}
