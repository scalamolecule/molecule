package molecule.sql.postgres.test.pagination.offset

import molecule.coreTests.test.pagination.offset._
import molecule.sql.postgres.setup.TestAsync_postgres

object OffsetBackwards extends OffsetBackwards with TestAsync_postgres
object OffsetForward extends OffsetForward with TestAsync_postgres
object OffsetSemantics extends OffsetSemantics with TestAsync_postgres
