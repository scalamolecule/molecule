package molecule.sql.mariadb.compliance.crud.delete

import molecule.coreTests.spi.crud.delete._
import molecule.sql.mariadb.setup.Test_mariadb_async

object Test_Delete_id extends Delete_id with Test_mariadb_async
object Test_Delete_filter extends Delete_filter with Test_mariadb_async
