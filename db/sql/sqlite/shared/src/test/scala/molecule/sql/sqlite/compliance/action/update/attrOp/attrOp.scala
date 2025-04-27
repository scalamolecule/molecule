package molecule.sql.sqlite.compliance.action.update.attrOp

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.update.attrOp.*
import molecule.sql.sqlite.setup.Api_sqlite_async


class AttrOp_BooleanTest extends Test {
  AttrOp_Boolean(this, Api_sqlite_async)
}
class AttrOp_StringTest extends Test {
  AttrOp_String(this, Api_sqlite_async)
}

