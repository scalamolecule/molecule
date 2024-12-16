package molecule.sql.postgres.compliance.crud

import molecule.coreTests.spi.crud.Transactions
import molecule.sql.postgres.setup.Test_postgres_async


object Test_Transactions extends Transactions with Test_postgres_async
