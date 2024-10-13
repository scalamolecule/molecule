package molecule.sql.sqlite.compliance.crud.delete

import molecule.coreTests.spi.crud.delete._
import molecule.sql.sqlite.setup.Test_sqlite_async

object Test_Delete_id extends Delete_id with Test_sqlite_async
object Test_Delete_filter extends Delete_filter with Test_sqlite_async
