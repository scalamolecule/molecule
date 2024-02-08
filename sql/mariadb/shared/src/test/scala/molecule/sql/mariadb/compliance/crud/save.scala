package molecule.sql.mariadb.compliance.crud

import molecule.coreTests.spi.crud.save._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object SaveCardOne extends SaveCardOne with TestAsync_mariadb
object SaveCardSet extends SaveCardSet with TestAsync_mariadb
object SaveRefs extends SaveRefs with TestAsync_mariadb
object SaveRefsOwned extends SaveRefsOwned with TestAsync_mariadb
object SaveSemantics extends SaveSemantics with TestAsync_mariadb
