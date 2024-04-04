package molecule.sql.mysql.compliance.crud

import molecule.coreTests.spi.crud.update.map._
import molecule.sql.mysql.setup.TestAsync_mysql

object UpdateMap_id extends UpdateMap_id with TestAsync_mysql
object UpdateMap_filter extends UpdateMap_filter with TestAsync_mysql
object UpdateMap_uniqueAttr extends UpdateMap_uniqueAttr with TestAsync_mysql
