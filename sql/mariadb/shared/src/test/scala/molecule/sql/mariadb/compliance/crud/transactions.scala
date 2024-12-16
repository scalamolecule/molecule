package molecule.sql.mariadb.compliance.crud

import molecule.coreTests.spi.crud.Transactions
import molecule.sql.mariadb.setup.Test_mariadb_async


object Test_Transactions extends Transactions with Test_mariadb_async
