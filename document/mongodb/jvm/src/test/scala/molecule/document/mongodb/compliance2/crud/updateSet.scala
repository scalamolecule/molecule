package molecule.document.mongodb.compliance2.crud

import molecule.coreTests.spi.crud.update.set._
import molecule.document.mongodb.setup.TestAsync_mongodb2

object UpdateSet_id extends UpdateSet_id with TestAsync_mongodb2
object UpdateSet_filter extends UpdateSet_filter with TestAsync_mongodb2
object UpdateSet_uniqueAttr extends UpdateSet_uniqueAttr with TestAsync_mongodb2
