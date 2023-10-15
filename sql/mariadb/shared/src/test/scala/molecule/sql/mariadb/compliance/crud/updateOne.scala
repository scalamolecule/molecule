package molecule.sql.mariadb.compliance.crud

import molecule.coreTests.compliance.crud.update.one._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object UpdateOne_id extends UpdateOne_id with TestAsync_mariadb
object UpdateOne_filter extends UpdateOne_filter with TestAsync_mariadb
object UpdateOne_uniqueAttr extends UpdateOne_uniqueAttr with TestAsync_mariadb
