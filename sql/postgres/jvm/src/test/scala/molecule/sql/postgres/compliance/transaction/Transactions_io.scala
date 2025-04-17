package molecule.sql.postgres.compliance.transaction

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action._
import molecule.sql.postgres.setup.Api_postgres_io


class Transactions_ioTest extends Test {
  Transactions_io(this, Api_postgres_io)
}
