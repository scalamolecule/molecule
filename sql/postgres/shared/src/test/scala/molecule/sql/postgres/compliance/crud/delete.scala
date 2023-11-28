package molecule.sql.postgres.compliance.crud

import molecule.coreTests.spi.crud.delete._
import molecule.sql.postgres.setup.TestAsync_postgres

object Delete_id extends Delete_id with TestAsync_postgres
object Delete_id_ref extends Delete_id_ref with TestAsync_postgres
object Delete_filter extends Delete_filter with TestAsync_postgres
object Delete_uniqueAttr extends Delete_uniqueAttr with TestAsync_postgres
