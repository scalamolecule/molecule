package molecule.sql.h2.compliance.pagination.cursor

import molecule.coreTests.spi.pagination.cursor.SharedSemantics
import molecule.sql.h2.setup.Test_h2_async

object Test_SharedSemantics extends SharedSemantics with Test_h2_async
