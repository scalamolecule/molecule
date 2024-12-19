package molecule.sql.mysql.compliance.transaction

import molecule.coreTests.spi.transaction.Transactions_zio
import molecule.sql.mysql.setup.Test_mysql_zio


object Test_Transactions_zio extends Transactions_zio with Test_mysql_zio
