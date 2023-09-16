package molecule.sql.h2.test.pagination.offset

import molecule.coreTests.test.pagination.offset._
import molecule.sql.h2.setup.TestAsync_h2

object OffsetBackwards extends OffsetBackwards with TestAsync_h2
object OffsetForward extends OffsetForward with TestAsync_h2
object OffsetSemantics extends OffsetSemantics with TestAsync_h2
