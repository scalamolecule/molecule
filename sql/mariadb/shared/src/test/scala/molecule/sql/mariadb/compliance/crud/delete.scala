package molecule.sql.mariadb.compliance.crud

import molecule.coreTests.spi.crud.delete._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object Delete_id extends Delete_id with TestAsync_mariadb
object Delete_id_ref extends Delete_id_ref with TestAsync_mariadb
object Delete_filter extends Delete_filter with TestAsync_mariadb
object Delete_uniqueAttr extends Delete_uniqueAttr with TestAsync_mariadb
