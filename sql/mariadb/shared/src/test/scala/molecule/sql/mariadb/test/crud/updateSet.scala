package molecule.sql.mariadb.test.crud

import molecule.coreTests.test.crud.update.set._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object UpdateSet_id extends UpdateSet_id with TestAsync_mariadb
object UpdateSet_filter extends UpdateSet_filter with TestAsync_mariadb
object UpdateSet_uniqueAttr extends UpdateSet_uniqueAttr with TestAsync_mariadb