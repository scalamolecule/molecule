package molecule.sql.mysql.compliance.action.update.attrOp

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.update.attrOp.*
import molecule.sql.mysql.setup.Api_mysql_async


class AttrOp_BooleanTest extends Test {
  AttrOp_Boolean(this, Api_mysql_async)
}
class AttrOp_StringTest extends Test {
  AttrOp_String(this, Api_mysql_async)
}

