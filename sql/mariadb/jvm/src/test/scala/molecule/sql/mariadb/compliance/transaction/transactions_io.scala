package molecule.sql.mariadb.compliance.transaction

import molecule.coreTests.spi.transaction.Transactions_io
import molecule.sql.mariadb.setup.Test_mariadb_io


object Test_Transactions_io extends Transactions_io with Test_mariadb_io
