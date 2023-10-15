package molecule.sql.postgres.compliance.time

import molecule.coreTests.compliance.time.GetSince
import molecule.sql.postgres.setup.TestAsync_postgres

object GetSince extends GetSince with TestAsync_postgres
