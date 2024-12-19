package molecule.sql.h2.compliance.transaction

import molecule.coreTests.spi.transaction.Transactions_sync
import molecule.sql.h2.setup.Test_h2_sync


object Test_Transactions_sync extends Transactions_sync with Test_h2_sync
