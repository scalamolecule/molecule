package molecule.sql.mysql.compliance.crud

import molecule.coreTests.spi.crud.Transactions
import molecule.sql.mysql.setup.Test_mysql_async


object Test_Transactions extends Transactions with Test_mysql_async
