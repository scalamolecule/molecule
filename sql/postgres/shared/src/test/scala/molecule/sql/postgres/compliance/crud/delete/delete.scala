package molecule.sql.postgres.compliance.crud.delete

import molecule.coreTests.spi.crud.delete._
import molecule.sql.postgres.setup.Test_postgres_async

object Test_Delete_id extends Delete_id with Test_postgres_async
object Test_Delete_filter extends Delete_filter with Test_postgres_async
object Test_Delete_uniqueAttr extends Delete_uniqueAttr with Test_postgres_async
