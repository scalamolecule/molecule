package molecule.db.mariadb.compliance.pagination.offset

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.pagination.offset.{Offset, OffsetSemantics}
import molecule.db.mariadb.setup.Api_mariadb_async

class OffsetTest extends MUnit {
  Offset(this, Api_mariadb_async)
}
class OffsetSemanticsTest extends MUnit {
  OffsetSemantics(this, Api_mariadb_async)
}
