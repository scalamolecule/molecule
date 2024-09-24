package molecule.sql.postgres.compliance.api

import molecule.coreTests.spi.api.SyncApi
import molecule.sql.postgres.setup.Test_postgres_sync

object Test_SyncApi extends SyncApi with Test_postgres_sync
