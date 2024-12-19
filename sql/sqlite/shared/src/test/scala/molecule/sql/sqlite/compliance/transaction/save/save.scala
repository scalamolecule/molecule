package molecule.sql.sqlite.compliance.transaction.save

import molecule.coreTests.spi.transaction.save._
import molecule.sql.sqlite.setup.{Test_sqlite_async, TestSuite_sqlite_array}
import molecule.sql.sqlite.spi.Spi_sqlite_async

object Test_SaveCardOne extends SaveCardOne with Test_sqlite_async
object Test_SaveCardSeq extends SaveCardSeq with TestSuite_sqlite_array with Spi_sqlite_async
object Test_SaveCardSet extends SaveCardSet with Test_sqlite_async
object Test_SaveCardMap extends SaveCardMap with Test_sqlite_async
object Test_SaveRefs extends SaveRefs with Test_sqlite_async
object Test_SaveSemantics extends SaveSemantics with Test_sqlite_async
