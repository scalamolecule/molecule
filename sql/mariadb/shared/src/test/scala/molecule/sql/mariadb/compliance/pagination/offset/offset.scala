package molecule.sql.mariadb.compliance.pagination.offset

import molecule.coreTests.compliance.pagination.offset._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object OffsetBackwards extends OffsetBackwards with TestAsync_mariadb
object OffsetForward extends OffsetForward with TestAsync_mariadb
object OffsetSemantics extends OffsetSemantics with TestAsync_mariadb
