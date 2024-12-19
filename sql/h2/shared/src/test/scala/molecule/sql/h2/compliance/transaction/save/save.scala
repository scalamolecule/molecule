package molecule.sql.h2.compliance.transaction.save

import molecule.coreTests.spi.transaction.save._
import molecule.sql.h2.setup.{Test_h2_async, TestSuite_h2_array}
import molecule.sql.h2.spi.Spi_h2_async

object Test_SaveCardOne extends SaveCardOne with Test_h2_async
object Test_SaveCardSeq extends SaveCardSeq with TestSuite_h2_array with Spi_h2_async
object Test_SaveCardSet extends SaveCardSet with Test_h2_async
object Test_SaveCardMap extends SaveCardMap with Test_h2_async
object Test_SaveRefs extends SaveRefs with Test_h2_async
object Test_SaveSemantics extends SaveSemantics with Test_h2_async
