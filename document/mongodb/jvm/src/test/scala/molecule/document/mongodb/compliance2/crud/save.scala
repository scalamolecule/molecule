package molecule.document.mongodb.compliance2.crud

import molecule.coreTests.spi.crud.save._
import molecule.document.mongodb.setup.TestAsync_mongodb2

object SaveCardOne extends SaveCardOne with TestAsync_mongodb2
object SaveCardSet extends SaveCardSet with TestAsync_mongodb2
object SaveRefs extends SaveRefs with TestAsync_mongodb2
object SaveSemantics extends SaveSemantics with TestAsync_mongodb2
