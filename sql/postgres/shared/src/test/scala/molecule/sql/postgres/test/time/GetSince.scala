package molecule.sql.postgres.test.time

import molecule.coreTests.test.time.GetSince
import molecule.sql.postgres.setup.TestAsync_postgres

object GetSince extends GetSince with TestAsync_postgres
