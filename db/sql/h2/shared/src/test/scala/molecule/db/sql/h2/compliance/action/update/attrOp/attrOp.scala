package molecule.db.sql.h2.compliance.action.update.attrOp

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.action.update.attrOp.{AttrOp_Boolean, AttrOp_String}
import molecule.db.sql
import molecule.db.sql.h2.setup.Api_h2_async


class AttrOp_BooleanTest extends Test {
  AttrOp_Boolean(this, Api_h2_async)
}
class AttrOp_StringTest extends Test {
  AttrOp_String(this, Api_h2_async)
}

