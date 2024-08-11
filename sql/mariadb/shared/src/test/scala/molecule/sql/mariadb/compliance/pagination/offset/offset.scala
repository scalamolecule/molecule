package molecule.sql.mariadb.compliance.pagination.offset

import molecule.coreTests.spi.pagination.offset._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object Test_OffsetBackwards extends OffsetBackwards with TestAsync_mariadb
object Test_OffsetForward extends OffsetForward with TestAsync_mariadb
object Test_OffsetSemantics extends OffsetSemantics with TestAsync_mariadb
