package molecule.sql.h2.compliance.transaction

import molecule.coreTests.spi.transaction.Transactions_io
import molecule.sql.h2.setup.Test_h2_io


object Test_Transactions_io extends Transactions_io with Test_h2_io
