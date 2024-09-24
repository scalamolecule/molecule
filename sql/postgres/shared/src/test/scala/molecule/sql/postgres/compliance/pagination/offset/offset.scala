package molecule.sql.postgres.compliance.pagination.offset

import molecule.coreTests.spi.pagination.offset._
import molecule.sql.postgres.setup.Test_postgres_async

object Test_OffsetBackwards extends OffsetBackwards with Test_postgres_async
object Test_OffsetForward extends OffsetForward with Test_postgres_async
object Test_OffsetSemantics extends OffsetSemantics with Test_postgres_async
