package molecule.sql.postgres.compliance.crud.delete

import molecule.coreTests.spi.crud.delete._
import molecule.sql.postgres.setup.TestAsync_postgres

object Test_Delete_id extends Delete_id with TestAsync_postgres
object Test_Delete_filter extends Delete_filter with TestAsync_postgres
object Test_Delete_uniqueAttr extends Delete_uniqueAttr with TestAsync_postgres
