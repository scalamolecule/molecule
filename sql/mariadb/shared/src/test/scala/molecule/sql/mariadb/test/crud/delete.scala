package molecule.sql.mariadb.test.crud

import molecule.coreTests.test.crud.delete._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object Delete_id extends Delete_id with TestAsync_mariadb
object Delete_filter extends Delete_filter with TestAsync_mariadb
object Delete_uniqueAttr extends Delete_uniqueAttr with TestAsync_mariadb
