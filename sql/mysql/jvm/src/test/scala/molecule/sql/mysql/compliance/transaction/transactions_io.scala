package molecule.sql.mysql.compliance.transaction

import molecule.coreTests.spi.transaction.Transactions_io
import molecule.sql.mysql.setup.Test_mysql_io


object Test_Transactions_io extends Transactions_io with Test_mysql_io
