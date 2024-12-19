package molecule.sql.postgres.compliance.transaction

import molecule.coreTests.spi.transaction.Transactions_zio
import molecule.sql.postgres.setup.Test_postgres_zio


object Test_Transactions_zio extends Transactions_zio with Test_postgres_zio
