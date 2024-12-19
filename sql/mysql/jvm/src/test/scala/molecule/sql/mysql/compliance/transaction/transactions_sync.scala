package molecule.sql.mysql.compliance.transaction

import molecule.coreTests.spi.transaction.Transactions_sync
import molecule.sql.mysql.setup.Test_mysql_sync


object Test_Transactions_sync extends Transactions_sync with Test_mysql_sync
