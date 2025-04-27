package molecule.db.sql.postgres.compliance.action.update.attrOp

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.update.attrOp.*
import molecule.db.sql
import molecule.db.sql.postgres.setup.Api_postgres_async


class AttrOp_BooleanTest extends Test {
  AttrOp_Boolean(this, Api_postgres_async)
}
class AttrOp_StringTest extends Test {
  AttrOp_String(this, Api_postgres_async)
}

