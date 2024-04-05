package molecule.sql.h2.compliance.crud

import molecule.coreTests.spi.crud.insert._
import molecule.sql.h2.setup.{TestAsync_h2, TestSuiteArray_h2}
import molecule.sql.h2.spi.SpiAsync_h2

object InsertCardOne extends InsertCardOne with TestAsync_h2
object InsertCardSet extends InsertCardSet with TestAsync_h2
object InsertCardMap extends InsertCardMap with TestAsync_h2
object InsertCardSeq extends InsertCardSeq with TestSuiteArray_h2 with SpiAsync_h2
object InsertRefs extends InsertRefs with TestAsync_h2
object InsertRefsOwned extends InsertRefsOwned with TestAsync_h2
object InsertSemantics extends InsertSemantics with TestAsync_h2
