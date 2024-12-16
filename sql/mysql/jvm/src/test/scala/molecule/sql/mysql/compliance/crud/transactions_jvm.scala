package molecule.sql.mysql.compliance.crud

import molecule.coreTests.spi.crud.Transactions_jvm
import molecule.sql.mysql.setup.Test_mysql_sync


object Test_Transactions_jvm extends Transactions_jvm with Test_mysql_sync
