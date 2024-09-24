package molecule.sql.sqlite.compliance.pagination.offset

import molecule.coreTests.spi.pagination.offset._
import molecule.sql.sqlite.setup.Test_sqlite_async

object Test_OffsetBackwards extends OffsetBackwards with Test_sqlite_async
object Test_OffsetForward extends OffsetForward with Test_sqlite_async
object Test_OffsetSemantics extends OffsetSemantics with Test_sqlite_async
