package molecule.sql.sqlite.compliance.transaction

import molecule.coreTests.spi.transaction.Transactions_io
import molecule.sql.sqlite.setup.Test_sqlite_io


object Test_Transactions_io extends Transactions_io with Test_sqlite_io
