package molecule.sql.h2.compliance.crud

import molecule.coreTests.spi.crud.Transactions
import molecule.sql.h2.setup.Test_h2_async


object Test_Transactions extends Transactions with Test_h2_async
