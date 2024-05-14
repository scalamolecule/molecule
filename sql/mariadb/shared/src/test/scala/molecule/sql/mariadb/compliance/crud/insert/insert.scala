package molecule.sql.mariadb.compliance.crud.insert

import molecule.coreTests.spi.crud.insert._
import molecule.sql.mariadb.setup.{TestAsync_mariadb, TestSuiteArray_mariadb}
import molecule.sql.mariadb.spi.SpiAsync_mariadb

object InsertCardOne extends InsertCardOne with TestAsync_mariadb
object InsertCardSeq extends InsertCardSeq with TestSuiteArray_mariadb with SpiAsync_mariadb
object InsertCardSet extends InsertCardSet with TestAsync_mariadb
object InsertCardMap extends InsertCardMap with TestAsync_mariadb
object InsertRefs extends InsertRefs with TestAsync_mariadb
object InsertSemantics extends InsertSemantics with TestAsync_mariadb
