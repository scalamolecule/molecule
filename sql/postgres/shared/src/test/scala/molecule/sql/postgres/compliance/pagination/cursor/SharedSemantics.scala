package molecule.sql.postgres.compliance.pagination.cursor

import molecule.coreTests.compliance.pagination.cursor.SharedSemantics
import molecule.sql.postgres.setup.TestAsync_postgres

object SharedSemantics extends SharedSemantics with TestAsync_postgres
