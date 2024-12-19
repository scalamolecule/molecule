package molecule.sql.sqlite.compliance.transaction

import molecule.coreTests.spi.transaction.Transactions_sync
import molecule.sql.sqlite.setup.Test_sqlite_sync


object Test_Transactions_sync extends Transactions_sync with Test_sqlite_sync
