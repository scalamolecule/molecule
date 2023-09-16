package molecule.sql.postgres.test.api

import molecule.coreTests.test.api.SyncApi
import molecule.sql.postgres.setup.TestSync_postgres

object SyncApi extends SyncApi with TestSync_postgres
