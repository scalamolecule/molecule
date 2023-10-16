package molecule.sql.mysql.compliance.time

import molecule.coreTests.spi.time.GetSince
import molecule.sql.mysql.setup.TestAsync_mysql

object GetSince extends GetSince with TestAsync_mysql
