package molecule.sql.mariadb.compliance.crud.save

import molecule.coreTests.spi.crud.save._
import molecule.sql.mariadb.setup.{TestAsync_mariadb, TestSuiteArray_mariadb}
import molecule.sql.mariadb.spi.SpiAsync_mariadb

object Test_SaveCardOne extends SaveCardOne with TestAsync_mariadb
object Test_SaveCardSeq extends SaveCardSeq with TestSuiteArray_mariadb with SpiAsync_mariadb
object Test_SaveCardSet extends SaveCardSet with TestAsync_mariadb
object Test_SaveCardMap extends SaveCardMap with TestAsync_mariadb
object Test_SaveRefs extends SaveRefs with TestAsync_mariadb
object Test_SaveSemantics extends SaveSemantics with TestAsync_mariadb
