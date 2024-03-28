package molecule.document.mongodb.compliance.crud

import molecule.coreTests.spi.crud.insert._
import molecule.document.mongodb.setup.{TestAsync_mongodb, TestSuiteArray_mongodb}
import molecule.document.mongodb.spi.SpiAsync_mongodb

object InsertCardOne extends InsertCardOne with TestAsync_mongodb
object InsertCardSet extends InsertCardSet with TestAsync_mongodb
//object InsertCardSeq extends InsertCardSeq with TestSuiteArray_mongodb with SpiAsync_mongodb
//object InsertCardMap extends InsertCardMap with TestAsync_mongodb
object InsertRefs extends InsertRefs with TestAsync_mongodb
object InsertRefsOwned extends InsertRefsOwned with TestAsync_mongodb
object InsertSemantics extends InsertSemantics with TestAsync_mongodb
