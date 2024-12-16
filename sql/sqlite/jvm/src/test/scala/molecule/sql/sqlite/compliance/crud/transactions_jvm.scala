package molecule.sql.sqlite.compliance.crud

import molecule.coreTests.spi.crud.Transactions_jvm
import molecule.sql.sqlite.setup.Test_sqlite_sync


object Test_Transactions_jvm extends Transactions_jvm with Test_sqlite_sync
