package molecule.db.sql.postgres.compliance.transaction

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.*
import molecule.db.sql
import molecule.db.sql.postgres.setup.Api_postgres_io


class Transactions_ioTest extends Test {
  Transactions_io(this, Api_postgres_io)
}
