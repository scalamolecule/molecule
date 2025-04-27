package molecule.sql.postgres.compliance.transaction

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.*
import molecule.sql.postgres.setup.Api_postgres_async


class Transactions_asyncTest extends Test {
  Transactions_async(this, Api_postgres_async)
}
