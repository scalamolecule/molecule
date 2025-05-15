package molecule.db.sql.sqlite.compliance.action.update.attrOp

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.action.update.attrOp.{AttrOp_Boolean, AttrOp_String}
import molecule.db.sql.sqlite.setup.Api_sqlite_async


class AttrOp_BooleanTest extends Test {
  AttrOp_Boolean(this, Api_sqlite_async)
}
class AttrOp_StringTest extends Test {
  AttrOp_String(this, Api_sqlite_async)
}

