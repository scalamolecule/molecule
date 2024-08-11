package molecule.datalog.datomic.compliance.pagination.offset

import molecule.coreTests.spi.pagination.offset._
import molecule.datalog.datomic.setup.TestAsync_datomic

object Test_OffsetBackwards extends OffsetBackwards with TestAsync_datomic
object Test_OffsetForward extends OffsetForward with TestAsync_datomic
object Test_OffsetSemantics extends OffsetSemantics with TestAsync_datomic
