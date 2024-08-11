package molecule.sql.h2.compliance.pagination.offset

import molecule.coreTests.spi.pagination.offset._
import molecule.sql.h2.setup.TestAsync_h2

object Test_OffsetBackwards extends OffsetBackwards with TestAsync_h2
object Test_OffsetForward extends OffsetForward with TestAsync_h2
object Test_OffsetSemantics extends OffsetSemantics with TestAsync_h2
