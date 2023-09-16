package molecule.datalog.datomic.test.pagination.offset

import molecule.coreTests.test.pagination.offset._
import molecule.datalog.datomic.setup.TestAsync_datomic

object OffsetBackwards extends OffsetBackwards with TestAsync_datomic
object OffsetForward extends OffsetForward with TestAsync_datomic
object OffsetSemantics extends OffsetSemantics with TestAsync_datomic
