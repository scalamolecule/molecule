package molecule.sql.mysql.compliance.crud

import molecule.coreTests.spi.crud.save._
import molecule.sql.mysql.setup.{TestAsync_mysql, TestSuiteArray_mysql}
import molecule.sql.mysql.spi.SpiAsync_mysql

object SaveCardOne extends SaveCardOne with TestAsync_mysql
object SaveCardSet extends SaveCardSet with TestAsync_mysql
object SaveCardSeq extends SaveCardSeq with TestSuiteArray_mysql with SpiAsync_mysql
object SaveCardMap extends SaveCardMap with TestAsync_mysql
object SaveRefs extends SaveRefs with TestAsync_mysql
object SaveRefsOwned extends SaveRefsOwned with TestAsync_mysql
object SaveSemantics extends SaveSemantics with TestAsync_mysql
