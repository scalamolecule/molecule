package molecule.sql.sqlite.compliance.pagination.cursor

import molecule.coreTests.spi.pagination.cursor.SharedSemantics
import molecule.sql.sqlite.setup.Test_sqlite_async

object Test_SharedSemantics extends SharedSemantics with Test_sqlite_async
