package molecule.sql.postgres.compliance.crud

import molecule.coreTests.spi.crud.Transactions_jvm
import molecule.sql.postgres.setup.Test_postgres_sync


object Test_Transactions_jvm extends Transactions_jvm with Test_postgres_sync
