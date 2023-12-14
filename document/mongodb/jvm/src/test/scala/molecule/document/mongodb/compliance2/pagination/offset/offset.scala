package molecule.document.mongodb.compliance2.pagination.offset

import molecule.coreTests.spi.pagination.offset._
import molecule.document.mongodb.setup.TestAsync_mongodb2

object OffsetBackwards extends OffsetBackwards with TestAsync_mongodb2
object OffsetForward extends OffsetForward with TestAsync_mongodb2
object OffsetSemantics extends OffsetSemantics with TestAsync_mongodb2
