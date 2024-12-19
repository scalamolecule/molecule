package molecule.sql.mariadb.compliance.transaction

import molecule.coreTests.spi.transaction.Transactions_sync
import molecule.sql.mariadb.setup.Test_mariadb_sync


object Test_Transactions_sync extends Transactions_sync with Test_mariadb_sync
