package molecule.sql.h2.compliance.crud.insert

import molecule.coreTests.spi.crud.insert._
import molecule.sql.h2.setup.{TestAsync_h2, TestSuiteArray_h2}
import molecule.sql.h2.spi.SpiAsync_h2

object Test_InsertCardOne extends InsertCardOne with TestAsync_h2
object Test_InsertCardSet extends InsertCardSet with TestAsync_h2
object Test_InsertCardMap extends InsertCardMap with TestAsync_h2
object Test_InsertCardSeq extends InsertCardSeq with TestSuiteArray_h2 with SpiAsync_h2
object Test_InsertRefs extends InsertRefs with TestAsync_h2
object Test_InsertSemantics extends InsertSemantics with TestAsync_h2
