package molecule.sql.mysql.compliance.pagination.offset

import molecule.coreTests.spi.pagination.offset._
import molecule.sql.mysql.setup.Test_mysql_async

object Test_OffsetBackwards extends OffsetBackwards with Test_mysql_async
object Test_OffsetForward extends OffsetForward with Test_mysql_async
object Test_OffsetSemantics extends OffsetSemantics with Test_mysql_async
