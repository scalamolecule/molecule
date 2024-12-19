package molecule.sql.postgres.compliance.transaction

import molecule.coreTests.spi.transaction.Transactions_async
import molecule.sql.postgres.setup.Test_postgres_async


object Test_Transactions_async extends Transactions_async with Test_postgres_async
