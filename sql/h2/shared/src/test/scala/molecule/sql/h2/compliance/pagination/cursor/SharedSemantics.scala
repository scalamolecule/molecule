package molecule.sql.h2.compliance.pagination.cursor

import molecule.coreTests.spi.pagination.cursor.SharedSemantics
import molecule.sql.h2.setup.TestAsync_h2

object Test_SharedSemantics extends SharedSemantics with TestAsync_h2
