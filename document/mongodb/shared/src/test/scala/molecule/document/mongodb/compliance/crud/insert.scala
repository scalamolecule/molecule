package molecule.document.mongodb.compliance.crud

import molecule.coreTests.spi.crud.insert._
import molecule.document.mongodb.setup.TestAsync_mongodb

object InsertCardOne extends InsertCardOne with TestAsync_mongodb
object InsertCardSet extends InsertCardSet with TestAsync_mongodb
object InsertRefs extends InsertRefs with TestAsync_mongodb
object InsertSemantics extends InsertSemantics with TestAsync_mongodb
