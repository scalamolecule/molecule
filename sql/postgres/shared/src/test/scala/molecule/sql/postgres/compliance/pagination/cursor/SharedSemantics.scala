package molecule.sql.postgres.compliance.pagination.cursor

import molecule.coreTests.spi.pagination.cursor.SharedSemantics
import molecule.sql.postgres.setup.TestAsync_postgres

object Test_SharedSemantics extends SharedSemantics with TestAsync_postgres
