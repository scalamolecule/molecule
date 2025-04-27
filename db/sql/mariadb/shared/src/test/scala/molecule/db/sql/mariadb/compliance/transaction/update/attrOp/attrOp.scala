package molecule.db.sql.mariadb.compliance.transaction.update.attrOp

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.update.attrOp.*
import molecule.db.sql
import molecule.db.sql.mariadb.setup.Api_mariadb_async


class AttrOp_BooleanTest extends Test {
  AttrOp_Boolean(this, Api_mariadb_async)
}
class AttrOp_StringTest extends Test {
  AttrOp_String(this, Api_mariadb_async)
}

