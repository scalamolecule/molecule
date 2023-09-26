package molecule.sql.mysql.test.crud

import molecule.coreTests.test.crud.delete._
import molecule.sql.mysql.setup.TestAsync_mysql

object Delete_id extends Delete_id with TestAsync_mysql
object Delete_filter extends Delete_filter with TestAsync_mysql
object Delete_uniqueAttr extends Delete_uniqueAttr with TestAsync_mysql
