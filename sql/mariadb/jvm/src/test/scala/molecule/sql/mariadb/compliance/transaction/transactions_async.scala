package molecule.sql.mariadb.compliance.transaction

import molecule.coreTests.spi.transaction.Transactions_async
import molecule.sql.mariadb.setup.Test_mariadb_async


object Test_Transactions_async extends Transactions_async with Test_mariadb_async
