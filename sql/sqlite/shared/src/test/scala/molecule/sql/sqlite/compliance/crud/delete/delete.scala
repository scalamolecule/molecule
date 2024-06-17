package molecule.sql.sqlite.compliance.crud.delete

import molecule.coreTests.spi.crud.delete._
import molecule.sql.sqlite.setup.TestAsync_sqlite

object Delete_id extends Delete_id with TestAsync_sqlite
object Delete_filter extends Delete_filter with TestAsync_sqlite
object Delete_uniqueAttr extends Delete_uniqueAttr with TestAsync_sqlite
