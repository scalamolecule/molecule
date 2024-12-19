package molecule.sql.postgres.compliance.transaction

import molecule.coreTests.spi.transaction.Transactions_io
import molecule.sql.postgres.setup.Test_postgres_io


object Test_Transactions_io extends Transactions_io with Test_postgres_io
