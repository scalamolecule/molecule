package molecule.datalog.datomic.test.pagination.offset

import molecule.coreTests.test.pagination.offset._
import molecule.datalog.datomic.setup.CoreTestAsync

object OffsetBackwards extends OffsetBackwards with CoreTestAsync
object OffsetForward extends OffsetForward with CoreTestAsync
object OffsetSemantics extends OffsetSemantics with CoreTestAsync
