package molecule.sql.postgres.compliance.transaction.update.attrOp

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.transaction.update.attrOp._
import molecule.sql.postgres.setup.Api_postgres_async


class AttrOp_Boolean extends Test {
  AttrOp_Boolean(this, Api_postgres_async)
}
class AttrOp_String extends Test {
  AttrOp_String(this, Api_postgres_async)
}

