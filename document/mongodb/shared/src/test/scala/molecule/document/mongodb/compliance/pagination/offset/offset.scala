package molecule.document.mongodb.compliance.pagination.offset

import molecule.coreTests.spi.pagination.offset._
import molecule.document.mongodb.setup.TestAsync_mongodb

object OffsetBackwards extends OffsetBackwards with TestAsync_mongodb
object OffsetForward extends OffsetForward with TestAsync_mongodb
object OffsetSemantics extends OffsetSemantics with TestAsync_mongodb
