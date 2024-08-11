package molecule.sql.sqlite.compliance.pagination.cursor

import molecule.coreTests.spi.pagination.cursor.SharedSemantics
import molecule.sql.sqlite.setup.TestAsync_sqlite

object Test_SharedSemantics extends SharedSemantics with TestAsync_sqlite
