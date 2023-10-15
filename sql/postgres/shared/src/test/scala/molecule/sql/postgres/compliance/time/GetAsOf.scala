package molecule.sql.postgres.compliance.time

import molecule.coreTests.compliance.time.GetAsOf
import molecule.sql.postgres.setup.TestAsync_postgres

object GetAsOf extends GetAsOf with TestAsync_postgres
