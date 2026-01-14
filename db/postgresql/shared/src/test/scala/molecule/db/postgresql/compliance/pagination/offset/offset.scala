package molecule.db.postgresql.compliance.pagination.offset

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.pagination.offset.{Offset, OffsetSemantics}
import molecule.db.postgresql.setup.Api_postgresql_async

class OffsetTest extends MUnit {
  Offset(this, Api_postgresql_async)
}
class OffsetSemanticsTest extends MUnit {
  OffsetSemantics(this, Api_postgresql_async)
}
