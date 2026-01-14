package molecule.db.sqlite.compliance.pagination.offset

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.pagination.offset.{Offset, OffsetSemantics}
import molecule.db.sqlite.setup.Api_sqlite_async

class OffsetTest extends MUnit {
  Offset(this, Api_sqlite_async)
}
class OffsetSemanticsTest extends MUnit {
  OffsetSemantics(this, Api_sqlite_async)
}
