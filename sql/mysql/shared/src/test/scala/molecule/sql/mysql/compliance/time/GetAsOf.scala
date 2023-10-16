package molecule.sql.mysql.compliance.time

import molecule.coreTests.spi.time.GetAsOf
import molecule.sql.mysql.setup.TestAsync_mysql

object GetAsOf extends GetAsOf with TestAsync_mysql
