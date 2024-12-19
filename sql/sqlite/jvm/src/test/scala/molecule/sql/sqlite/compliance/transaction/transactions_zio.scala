package molecule.sql.sqlite.compliance.transaction

import molecule.coreTests.spi.transaction.Transactions_zio
import molecule.sql.sqlite.setup.Test_sqlite_zio


object Test_Transactions_zio extends Transactions_zio with Test_sqlite_zio
