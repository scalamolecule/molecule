package molecule.sql.postgres.compliance.api

import molecule.coreTests.spi.api.SyncApi
import molecule.sql.postgres.setup.TestSync_postgres

object Test_SyncApi extends SyncApi with TestSync_postgres
