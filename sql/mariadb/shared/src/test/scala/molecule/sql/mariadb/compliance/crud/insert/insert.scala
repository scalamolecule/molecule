package molecule.sql.mariadb.compliance.crud.insert

import molecule.coreTests.spi.crud.insert._
import molecule.sql.mariadb.setup.{TestAsync_mariadb, TestSuiteArray_mariadb}
import molecule.sql.mariadb.spi.SpiAsync_mariadb

object Test_InsertCardOne extends InsertCardOne with TestAsync_mariadb
object Test_InsertCardSeq extends InsertCardSeq with TestSuiteArray_mariadb with SpiAsync_mariadb
object Test_InsertCardSet extends InsertCardSet with TestAsync_mariadb
object Test_InsertCardMap extends InsertCardMap with TestAsync_mariadb
object Test_InsertRefs extends InsertRefs with TestAsync_mariadb
object Test_InsertSemantics extends InsertSemantics with TestAsync_mariadb
