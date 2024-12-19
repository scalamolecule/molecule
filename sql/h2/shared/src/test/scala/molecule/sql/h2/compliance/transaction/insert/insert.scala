package molecule.sql.h2.compliance.transaction.insert

import molecule.coreTests.spi.transaction.insert._
import molecule.sql.h2.setup.{TestSuite_h2_array, Test_h2_async}
import molecule.sql.h2.spi.Spi_h2_async

object Test_InsertCardOne extends InsertCardOne with Test_h2_async
object Test_InsertCardSet extends InsertCardSet with Test_h2_async
object Test_InsertCardMap extends InsertCardMap with Test_h2_async
object Test_InsertCardSeq extends InsertCardSeq with TestSuite_h2_array with Spi_h2_async
object Test_InsertRefs extends InsertRefs with Test_h2_async
object Test_InsertSemantics extends InsertSemantics with Test_h2_async
