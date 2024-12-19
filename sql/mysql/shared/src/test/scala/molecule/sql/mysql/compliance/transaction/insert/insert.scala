package molecule.sql.mysql.compliance.transaction.insert

import molecule.coreTests.spi.transaction.insert._
import molecule.sql.mysql.setup.{Test_mysql_async, TestSuite_mysql_array}
import molecule.sql.mysql.spi.Spi_mysql_async

object Test_InsertCardOne extends InsertCardOne with Test_mysql_async
object Test_InsertCardSeq extends InsertCardSeq with TestSuite_mysql_array with Spi_mysql_async
object Test_InsertCardSet extends InsertCardSet with Test_mysql_async
object Test_InsertCardMap extends InsertCardMap with Test_mysql_async
object Test_InsertRefs extends InsertRefs with Test_mysql_async
object Test_InsertSemantics extends InsertSemantics with Test_mysql_async
