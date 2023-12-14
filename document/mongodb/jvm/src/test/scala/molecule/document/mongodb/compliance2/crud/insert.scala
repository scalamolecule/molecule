package molecule.document.mongodb.compliance2.crud

import molecule.coreTests.spi.crud.insert._
import molecule.document.mongodb.setup.TestAsync_mongodb2

object InsertCardOne extends InsertCardOne with TestAsync_mongodb2
object InsertCardSet extends InsertCardSet with TestAsync_mongodb2
object InsertRefs extends InsertRefs with TestAsync_mongodb2
object InsertSemantics extends InsertSemantics with TestAsync_mongodb2
