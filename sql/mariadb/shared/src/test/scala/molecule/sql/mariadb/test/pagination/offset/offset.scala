package molecule.sql.mariadb.test.pagination.offset

import molecule.coreTests.test.pagination.offset._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object OffsetBackwards extends OffsetBackwards with TestAsync_mariadb
object OffsetForward extends OffsetForward with TestAsync_mariadb
object OffsetSemantics extends OffsetSemantics with TestAsync_mariadb
