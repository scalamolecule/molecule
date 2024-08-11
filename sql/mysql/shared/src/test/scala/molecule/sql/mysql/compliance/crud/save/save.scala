package molecule.sql.mysql.compliance.crud.save

import molecule.coreTests.spi.crud.save._
import molecule.sql.mysql.setup.{TestAsync_mysql, TestSuiteArray_mysql}
import molecule.sql.mysql.spi.SpiAsync_mysql

object Test_SaveCardOne extends SaveCardOne with TestAsync_mysql
object Test_SaveCardSeq extends SaveCardSeq with TestSuiteArray_mysql with SpiAsync_mysql
object Test_SaveCardSet extends SaveCardSet with TestAsync_mysql
object Test_SaveCardMap extends SaveCardMap with TestAsync_mysql
object Test_SaveRefs extends SaveRefs with TestAsync_mysql
object Test_SaveSemantics extends SaveSemantics with TestAsync_mysql
