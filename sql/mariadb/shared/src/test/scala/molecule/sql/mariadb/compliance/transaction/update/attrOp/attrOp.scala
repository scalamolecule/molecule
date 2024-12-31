package molecule.sql.mariadb.compliance.transaction.update.attrOp

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.action.update.attrOp._
import molecule.sql.mariadb.setup.Api_mariadb_async


class AttrOp_Boolean extends MUnitSuite {
  AttrOp_Boolean(this, Api_mariadb_async)
}
class AttrOp_String extends MUnitSuite {
  AttrOp_String(this, Api_mariadb_async)
}

