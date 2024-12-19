package molecule.sql.h2.compliance.transaction

import molecule.coreTests.spi.transaction.Transactions_zio
import molecule.sql.h2.setup.Test_h2_zio


object Test_Transactions_zio extends Transactions_zio with Test_h2_zio
