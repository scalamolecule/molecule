package molecule.sql.mysql.test.crud

import molecule.coreTests.test.crud.insert._
import molecule.sql.mysql.setup.TestAsync_mysql

object InsertCardOne extends InsertCardOne with TestAsync_mysql
object InsertCardSet extends InsertCardSet with TestAsync_mysql
object InsertRefs extends InsertRefs with TestAsync_mysql
object InsertSemantics extends InsertSemantics with TestAsync_mysql
