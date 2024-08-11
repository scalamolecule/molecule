package molecule.sql.mysql.compliance.pagination.offset

import molecule.coreTests.spi.pagination.offset._
import molecule.sql.mysql.setup.TestAsync_mysql

object Test_OffsetBackwards extends OffsetBackwards with TestAsync_mysql
object Test_OffsetForward extends OffsetForward with TestAsync_mysql
object Test_OffsetSemantics extends OffsetSemantics with TestAsync_mysql
