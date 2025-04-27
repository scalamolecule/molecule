package molecule.db.sql.h2.compliance.action.update.attrOp

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.update.attrOp.*
import molecule.db.sql
import molecule.db.sql.h2.setup.Api_h2_async


class AttrOp_BooleanTest extends Test {
  AttrOp_Boolean(this, Api_h2_async)
}
class AttrOp_StringTest extends Test {
  AttrOp_String(this, Api_h2_async)
}

