package molecule.sql.mariadb.compliance.pagination.cursor

import molecule.coreTests.spi.pagination.cursor.SharedSemantics
import molecule.sql.mariadb.setup.Test_mariadb_async

object Test_SharedSemantics extends SharedSemantics with Test_mariadb_async
