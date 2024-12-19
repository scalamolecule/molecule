package molecule.sql.postgres.compliance.transaction

import molecule.coreTests.spi.transaction.Transactions_sync
import molecule.sql.postgres.setup.Test_postgres_sync


object Test_Transactions_sync extends Transactions_sync with Test_postgres_sync
