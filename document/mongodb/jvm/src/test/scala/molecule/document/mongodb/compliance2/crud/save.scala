package molecule.document.mongodb.compliance2.crud

import molecule.coreTests.spi.crud.save._
import molecule.document.mongodb.setup.TestAsync_mongodb

object SaveCardOne extends SaveCardOne with TestAsync_mongodb
object SaveCardSet extends SaveCardSet with TestAsync_mongodb
object SaveRefs extends SaveRefs with TestAsync_mongodb
object SaveSemantics extends SaveSemantics with TestAsync_mongodb
