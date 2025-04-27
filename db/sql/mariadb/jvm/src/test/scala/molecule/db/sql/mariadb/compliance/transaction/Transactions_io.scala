package molecule.db.sql.mariadb.compliance.transaction

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.*
import molecule.db.sql
import molecule.db.sql.mariadb.setup.Api_mariadb_io


class Transactions_ioTest extends Test {
  Transactions_io(this, Api_mariadb_io)
}
