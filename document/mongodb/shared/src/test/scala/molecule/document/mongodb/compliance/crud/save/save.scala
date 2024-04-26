package molecule.document.mongodb.compliance.crud.save

import molecule.coreTests.spi.crud.save._
import molecule.document.mongodb.setup.{TestAsync_mongodb, TestSuiteArray_mongodb}
import molecule.document.mongodb.spi.SpiAsync_mongodb

object SaveCardOne extends SaveCardOne with TestAsync_mongodb
object SaveCardSeq extends SaveCardSeq with TestSuiteArray_mongodb with SpiAsync_mongodb
object SaveCardSet extends SaveCardSet with TestAsync_mongodb
object SaveCardMap extends SaveCardMap with TestAsync_mongodb
object SaveRefs extends SaveRefs with TestAsync_mongodb
object SaveRefsOwned extends SaveRefsOwned with TestAsync_mongodb
object SaveSemantics extends SaveSemantics with TestAsync_mongodb
