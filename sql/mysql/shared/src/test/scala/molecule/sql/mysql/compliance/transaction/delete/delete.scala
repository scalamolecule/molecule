package molecule.sql.mysql.compliance.transaction.delete

import molecule.coreTests.spi.transaction.delete._
import molecule.sql.mysql.setup.Test_mysql_async

object Test_Delete_id extends Delete_id with Test_mysql_async
object Test_Delete_filter extends Delete_filter with Test_mysql_async
