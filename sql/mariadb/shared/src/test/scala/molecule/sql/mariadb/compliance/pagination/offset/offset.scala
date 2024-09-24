package molecule.sql.mariadb.compliance.pagination.offset

import molecule.coreTests.spi.pagination.offset._
import molecule.sql.mariadb.setup.Test_mariadb_async

object Test_OffsetBackwards extends OffsetBackwards with Test_mariadb_async
object Test_OffsetForward extends OffsetForward with Test_mariadb_async
object Test_OffsetSemantics extends OffsetSemantics with Test_mariadb_async
