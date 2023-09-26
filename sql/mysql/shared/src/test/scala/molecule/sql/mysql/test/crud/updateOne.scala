package molecule.sql.mysql.test.crud

import molecule.coreTests.test.crud.update.one._
import molecule.sql.mysql.setup.TestAsync_mysql

object UpdateOne_id extends UpdateOne_id with TestAsync_mysql
object UpdateOne_filter extends UpdateOne_filter with TestAsync_mysql
object UpdateOne_uniqueAttr extends UpdateOne_uniqueAttr with TestAsync_mysql
