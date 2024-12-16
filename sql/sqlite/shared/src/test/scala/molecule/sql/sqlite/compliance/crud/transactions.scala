package molecule.sql.sqlite.compliance.crud

import molecule.coreTests.spi.crud.Transactions
import molecule.sql.sqlite.setup.Test_sqlite_async


object Test_Transactions extends Transactions with Test_sqlite_async
