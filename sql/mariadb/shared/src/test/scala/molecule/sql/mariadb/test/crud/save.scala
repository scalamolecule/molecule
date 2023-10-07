package molecule.sql.mariadb.test.crud

import molecule.coreTests.test.crud.save._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object SaveCardOne extends SaveCardOne with TestAsync_mariadb
object SaveCardSet extends SaveCardSet with TestAsync_mariadb
object SaveRefs extends SaveRefs with TestAsync_mariadb
object SaveSemantics extends SaveSemantics with TestAsync_mariadb
