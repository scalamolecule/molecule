package molecule.sql.mariadb.compliance.time

import molecule.coreTests.spi.time.GetSince
import molecule.sql.mariadb.setup.TestAsync_mariadb

object GetSince extends GetSince with TestAsync_mariadb
