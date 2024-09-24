package molecule.sql.h2.compliance.pagination.offset

import molecule.coreTests.spi.pagination.offset._
import molecule.sql.h2.setup.Test_h2_async

object Test_OffsetBackwards extends OffsetBackwards with Test_h2_async
object Test_OffsetForward extends OffsetForward with Test_h2_async
object Test_OffsetSemantics extends OffsetSemantics with Test_h2_async
