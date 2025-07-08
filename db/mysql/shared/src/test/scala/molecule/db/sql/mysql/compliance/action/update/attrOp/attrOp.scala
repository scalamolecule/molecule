package molecule.db.sql.mysql.compliance.action.update.attrOp

import molecule.core.setup.MUnit
import molecule.db.compliance.test.action.update.attrOp.{AttrOp_Boolean, AttrOp_String}
import molecule.db.sql.mysql.setup.Api_mysql_async


class AttrOp_BooleanTest extends MUnit {
  AttrOp_Boolean(this, Api_mysql_async)
}
class AttrOp_StringTest extends MUnit {
  AttrOp_String(this, Api_mysql_async)
}

