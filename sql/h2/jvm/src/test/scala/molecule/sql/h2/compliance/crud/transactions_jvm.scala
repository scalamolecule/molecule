package molecule.sql.h2.compliance.crud

import molecule.coreTests.spi.crud.Transactions_jvm
import molecule.sql.h2.setup.Test_h2_sync


object Test_Transactions_jvm extends Transactions_jvm with Test_h2_sync
