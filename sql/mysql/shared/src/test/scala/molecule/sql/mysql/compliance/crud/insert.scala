package molecule.sql.mysql.compliance.crud

import molecule.coreTests.spi.crud.insert._
import molecule.sql.mysql.setup.{TestAsync_mysql, TestSuiteArray_mysql}
import molecule.sql.mysql.spi.SpiAsync_mysql

object InsertCardOne extends InsertCardOne with TestAsync_mysql
object InsertCardSet extends InsertCardSet with TestAsync_mysql
//object InsertCardSeq extends InsertCardSeq with TestSuiteArray_mysql with SpiAsync_mysql
//object InsertCardMap extends InsertCardMap with TestAsync_mysql
object InsertRefs extends InsertRefs with TestAsync_mysql
object InsertRefsOwned extends InsertRefsOwned with TestAsync_mysql
object InsertSemantics extends InsertSemantics with TestAsync_mysql
