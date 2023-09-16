package molecule.sql.postgres.test.crud

import molecule.coreTests.test.crud.delete._
import molecule.sql.postgres.setup.TestAsync_postgres

object Delete_id extends Delete_id with TestAsync_postgres
object Delete_filter extends Delete_filter with TestAsync_postgres
object Delete_uniqueAttr extends Delete_uniqueAttr with TestAsync_postgres
