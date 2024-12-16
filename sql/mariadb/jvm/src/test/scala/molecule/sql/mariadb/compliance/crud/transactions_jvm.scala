package molecule.sql.mariadb.compliance.crud

import molecule.coreTests.spi.crud.Transactions_jvm
import molecule.sql.mariadb.setup.Test_mariadb_sync


object Test_Transactions_jvm extends Transactions_jvm with Test_mariadb_sync
