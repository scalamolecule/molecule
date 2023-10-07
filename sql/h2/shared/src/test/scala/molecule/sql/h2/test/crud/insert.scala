package molecule.sql.h2.test.crud

import molecule.coreTests.test.crud.insert._
import molecule.sql.h2.setup.TestAsync_h2

object InsertCardOne extends InsertCardOne with TestAsync_h2
object InsertCardSet extends InsertCardSet with TestAsync_h2
object InsertRefs extends InsertRefs with TestAsync_h2
object InsertSemantics extends InsertSemantics with TestAsync_h2