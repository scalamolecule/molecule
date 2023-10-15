package molecule.sql.postgres.compliance.api

import molecule.coreTests.compliance.api.SyncApi
import molecule.sql.postgres.setup.TestSync_postgres

object SyncApi extends SyncApi with TestSync_postgres
