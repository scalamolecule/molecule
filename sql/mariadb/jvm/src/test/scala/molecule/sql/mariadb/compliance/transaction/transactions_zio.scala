package molecule.sql.mariadb.compliance.transaction

import molecule.coreTests.spi.transaction.Transactions_zio
import molecule.sql.mariadb.setup.Test_mariadb_zio


object Test_Transactions_zio extends Transactions_zio with Test_mariadb_zio
