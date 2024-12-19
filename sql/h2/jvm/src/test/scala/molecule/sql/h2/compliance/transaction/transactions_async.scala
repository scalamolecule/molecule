package molecule.sql.h2.compliance.transaction

import molecule.coreTests.spi.transaction.Transactions_async
import molecule.sql.h2.setup.Test_h2_async


object Test_Transactions_async extends Transactions_async with Test_h2_async
