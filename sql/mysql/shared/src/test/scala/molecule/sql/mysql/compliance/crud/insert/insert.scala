package molecule.sql.mysql.compliance.crud.insert

import molecule.coreTests.spi.crud.insert._
import molecule.sql.mysql.setup.{TestAsync_mysql, TestSuiteArray_mysql}
import molecule.sql.mysql.spi.SpiAsync_mysql

object Test_InsertCardOne extends InsertCardOne with TestAsync_mysql
object Test_InsertCardSeq extends InsertCardSeq with TestSuiteArray_mysql with SpiAsync_mysql
object Test_InsertCardSet extends InsertCardSet with TestAsync_mysql
object Test_InsertCardMap extends InsertCardMap with TestAsync_mysql
object Test_InsertRefs extends InsertRefs with TestAsync_mysql
object Test_InsertSemantics extends InsertSemantics with TestAsync_mysql
