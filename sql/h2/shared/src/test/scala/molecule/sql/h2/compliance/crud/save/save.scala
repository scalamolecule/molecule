package molecule.sql.h2.compliance.crud.save

import molecule.coreTests.spi.crud.save._
import molecule.sql.h2.setup.{TestAsync_h2, TestSuiteArray_h2}
import molecule.sql.h2.spi.SpiAsync_h2

object Test_SaveCardOne extends SaveCardOne with TestAsync_h2
object Test_SaveCardSeq extends SaveCardSeq with TestSuiteArray_h2 with SpiAsync_h2
object Test_SaveCardSet extends SaveCardSet with TestAsync_h2
object Test_SaveCardMap extends SaveCardMap with TestAsync_h2
object Test_SaveRefs extends SaveRefs with TestAsync_h2
object Test_SaveSemantics extends SaveSemantics with TestAsync_h2
