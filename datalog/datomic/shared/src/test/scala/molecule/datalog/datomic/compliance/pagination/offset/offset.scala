package molecule.datalog.datomic.compliance.pagination.offset

import molecule.coreTests.spi.pagination.offset._
import molecule.datalog.datomic.setup.TestAsync_datomic

object OffsetBackwards extends OffsetBackwards with TestAsync_datomic
object OffsetForward extends OffsetForward with TestAsync_datomic
object OffsetSemantics extends OffsetSemantics with TestAsync_datomic
