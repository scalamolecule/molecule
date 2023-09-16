package molecule.sql.postgres.test.pagination.cursor

import molecule.coreTests.test.pagination.cursor.SharedSemantics
import molecule.sql.postgres.setup.TestAsync_postgres

object SharedSemantics extends SharedSemantics with TestAsync_postgres
