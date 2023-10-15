package molecule.sql.mysql.compliance.crud

import molecule.coreTests.compliance.crud.update.set._
import molecule.sql.mysql.setup.TestAsync_mysql

object UpdateSet_id extends UpdateSet_id with TestAsync_mysql
object UpdateSet_filter extends UpdateSet_filter with TestAsync_mysql
object UpdateSet_uniqueAttr extends UpdateSet_uniqueAttr with TestAsync_mysql
