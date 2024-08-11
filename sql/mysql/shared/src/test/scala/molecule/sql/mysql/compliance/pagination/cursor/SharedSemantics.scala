package molecule.sql.mysql.compliance.pagination.cursor

import molecule.coreTests.spi.pagination.cursor.SharedSemantics
import molecule.sql.mysql.setup.TestAsync_mysql

object Test_SharedSemantics extends SharedSemantics with TestAsync_mysql
