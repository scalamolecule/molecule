package molecule.sql.sqlite.compliance.crud.insert

import molecule.coreTests.spi.crud.insert._
import molecule.sql.sqlite.setup.{Test_sqlite_async, TestSuite_sqlite_array}
import molecule.sql.sqlite.spi.Spi_sqlite_async

object Test_InsertCardOne extends InsertCardOne with Test_sqlite_async
object Test_InsertCardSet extends InsertCardSet with Test_sqlite_async
object Test_InsertCardMap extends InsertCardMap with Test_sqlite_async
object Test_InsertCardSeq extends InsertCardSeq with TestSuite_sqlite_array with Spi_sqlite_async
object Test_InsertRefs extends InsertRefs with Test_sqlite_async
object Test_InsertSemantics extends InsertSemantics with Test_sqlite_async
