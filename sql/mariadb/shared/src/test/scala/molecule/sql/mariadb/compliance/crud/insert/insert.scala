package molecule.sql.mariadb.compliance.crud.insert

import molecule.coreTests.spi.crud.insert._
import molecule.sql.mariadb.setup.{Test_mariadb_async, TestSuite_mariadb_array}
import molecule.sql.mariadb.spi.Spi_mariadb_async

object Test_InsertCardOne extends InsertCardOne with Test_mariadb_async
object Test_InsertCardSeq extends InsertCardSeq with TestSuite_mariadb_array with Spi_mariadb_async
object Test_InsertCardSet extends InsertCardSet with Test_mariadb_async
object Test_InsertCardMap extends InsertCardMap with Test_mariadb_async
object Test_InsertRefs extends InsertRefs with Test_mariadb_async
object Test_InsertSemantics extends InsertSemantics with Test_mariadb_async
