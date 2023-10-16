package molecule.sql.h2.compliance.pagination.offset

import molecule.coreTests.spi.pagination.offset._
import molecule.sql.h2.setup.TestAsync_h2

object OffsetBackwards extends OffsetBackwards with TestAsync_h2
object OffsetForward extends OffsetForward with TestAsync_h2
object OffsetSemantics extends OffsetSemantics with TestAsync_h2
