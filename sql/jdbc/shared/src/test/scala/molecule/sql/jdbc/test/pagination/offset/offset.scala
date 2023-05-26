package molecule.sql.jdbc.test.pagination.offset

import molecule.coreTests.test.pagination.offset._
import molecule.sql.jdbc.setup.CoreTestAsync

object OffsetBackwards extends OffsetBackwards with CoreTestAsync
object OffsetForward extends OffsetForward with CoreTestAsync
object OffsetSemantics extends OffsetSemantics with CoreTestAsync
