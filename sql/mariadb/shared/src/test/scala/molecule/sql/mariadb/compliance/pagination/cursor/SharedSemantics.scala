package molecule.sql.mariadb.compliance.pagination.cursor

import molecule.coreTests.spi.pagination.cursor.SharedSemantics
import molecule.sql.mariadb.setup.TestAsync_mariadb

object Test_SharedSemantics extends SharedSemantics with TestAsync_mariadb
