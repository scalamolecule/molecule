package molecule.sql.mariadb.compliance.crud

import molecule.coreTests.spi.crud.update.map._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object UpdateMap_id extends UpdateMap_id with TestAsync_mariadb
object UpdateMap_filter extends UpdateMap_filter with TestAsync_mariadb
object UpdateMap_uniqueAttr extends UpdateMap_uniqueAttr with TestAsync_mariadb
