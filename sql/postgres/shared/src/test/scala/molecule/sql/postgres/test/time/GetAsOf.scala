package molecule.sql.postgres.test.time

import molecule.coreTests.test.time.GetAsOf
import molecule.sql.postgres.setup.TestAsync_postgres

object GetAsOf extends GetAsOf with TestAsync_postgres
