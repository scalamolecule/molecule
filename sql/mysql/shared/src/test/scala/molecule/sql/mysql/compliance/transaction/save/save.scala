package molecule.sql.mysql.compliance.transaction.save

import molecule.coreTests.spi.transaction.save._
import molecule.sql.mysql.setup.{Test_mysql_async, TestSuite_mysql_array}
import molecule.sql.mysql.spi.Spi_mysql_async

object Test_SaveCardOne extends SaveCardOne with Test_mysql_async
object Test_SaveCardSeq extends SaveCardSeq with TestSuite_mysql_array with Spi_mysql_async
object Test_SaveCardSet extends SaveCardSet with Test_mysql_async
object Test_SaveCardMap extends SaveCardMap with Test_mysql_async
object Test_SaveRefs extends SaveRefs with Test_mysql_async
object Test_SaveSemantics extends SaveSemantics with Test_mysql_async
