package molecule.sql.sqlite.compliance.transaction

import molecule.coreTests.spi.transaction.Transactions_async
import molecule.sql.sqlite.setup.Test_sqlite_async


object Test_Transactions_async extends Transactions_async with Test_sqlite_async
