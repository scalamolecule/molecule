package molecule.sql.mariadb.test.crud

import molecule.coreTests.test.crud.insert._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object InsertCardOne extends InsertCardOne with TestAsync_mariadb
object InsertCardSet extends InsertCardSet with TestAsync_mariadb
object InsertRefs extends InsertRefs with TestAsync_mariadb
object InsertSemantics extends InsertSemantics with TestAsync_mariadb
