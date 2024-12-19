package molecule.sql.sqlite.compliance.transaction.insert

import molecule.coreTests.spi.transaction.insert._
import molecule.sql.sqlite.setup.{TestSuite_sqlite_array, Test_sqlite_async}
import molecule.sql.sqlite.spi.Spi_sqlite_async

object Test_InsertCardOne extends InsertCardOne with Test_sqlite_async
object Test_InsertCardSet extends InsertCardSet with Test_sqlite_async
object Test_InsertCardMap extends InsertCardMap with Test_sqlite_async
object Test_InsertCardSeq extends InsertCardSeq with TestSuite_sqlite_array with Spi_sqlite_async
object Test_InsertRefs extends InsertRefs with Test_sqlite_async
object Test_InsertSemantics extends InsertSemantics with Test_sqlite_async
