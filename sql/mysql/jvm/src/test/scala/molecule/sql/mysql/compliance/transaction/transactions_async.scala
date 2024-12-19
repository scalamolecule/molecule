package molecule.sql.mysql.compliance.transaction

import molecule.coreTests.spi.transaction.Transactions_async
import molecule.sql.mysql.setup.Test_mysql_async


object Test_Transactions_async extends Transactions_async with Test_mysql_async
