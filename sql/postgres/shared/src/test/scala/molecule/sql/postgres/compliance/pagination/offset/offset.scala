package molecule.sql.postgres.compliance.pagination.offset

import molecule.coreTests.spi.pagination.offset._
import molecule.sql.postgres.setup.TestAsync_postgres

object Test_OffsetBackwards extends OffsetBackwards with TestAsync_postgres
object Test_OffsetForward extends OffsetForward with TestAsync_postgres
object Test_OffsetSemantics extends OffsetSemantics with TestAsync_postgres
