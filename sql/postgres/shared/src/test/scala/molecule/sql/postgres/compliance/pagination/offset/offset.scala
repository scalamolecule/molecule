package molecule.sql.postgres.compliance.pagination.offset

import molecule.coreTests.compliance.pagination.offset._
import molecule.sql.postgres.setup.TestAsync_postgres

object OffsetBackwards extends OffsetBackwards with TestAsync_postgres
object OffsetForward extends OffsetForward with TestAsync_postgres
object OffsetSemantics extends OffsetSemantics with TestAsync_postgres
