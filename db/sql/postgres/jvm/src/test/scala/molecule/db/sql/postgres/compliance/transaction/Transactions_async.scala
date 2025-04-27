package molecule.db.sql.postgres.compliance.transaction

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.*
import molecule.db.sql
import molecule.db.sql.postgres.setup.Api_postgres_async


class Transactions_asyncTest extends Test {
  Transactions_async(this, Api_postgres_async)
}
