package molecule.datalog.datomic.compliance.pagination.offset

import molecule.coreTests.spi.pagination.offset._
import molecule.datalog.datomic.setup.Test_datomic_async

object Test_OffsetBackwards extends OffsetBackwards with Test_datomic_async
object Test_OffsetForward extends OffsetForward with Test_datomic_async
object Test_OffsetSemantics extends OffsetSemantics with Test_datomic_async
