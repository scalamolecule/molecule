package molecule.sql.mysql.compliance.pagination.offset

import molecule.coreTests.compliance.pagination.offset._
import molecule.sql.mysql.setup.TestAsync_mysql

object OffsetBackwards extends OffsetBackwards with TestAsync_mysql
object OffsetForward extends OffsetForward with TestAsync_mysql
object OffsetSemantics extends OffsetSemantics with TestAsync_mysql
