package molecule.sql.mariadb.compliance.time

import molecule.coreTests.spi.time.GetAsOf
import molecule.sql.mariadb.setup.TestAsync_mariadb

object GetAsOf extends GetAsOf with TestAsync_mariadb
