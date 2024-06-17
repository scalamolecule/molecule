package molecule.sql.sqlite.compliance.pagination.offset

import molecule.coreTests.spi.pagination.offset._
import molecule.sql.sqlite.setup.TestAsync_sqlite

object OffsetBackwards extends OffsetBackwards with TestAsync_sqlite
object OffsetForward extends OffsetForward with TestAsync_sqlite
object OffsetSemantics extends OffsetSemantics with TestAsync_sqlite
