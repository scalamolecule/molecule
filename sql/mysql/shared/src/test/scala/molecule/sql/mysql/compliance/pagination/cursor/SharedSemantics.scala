package molecule.sql.mysql.compliance.pagination.cursor

import molecule.coreTests.spi.pagination.cursor.SharedSemantics
import molecule.sql.mysql.setup.Test_mysql_async

object Test_SharedSemantics extends SharedSemantics with Test_mysql_async
