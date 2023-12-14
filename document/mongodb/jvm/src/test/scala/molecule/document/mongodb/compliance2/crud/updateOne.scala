package molecule.document.mongodb.compliance2.crud

import molecule.coreTests.spi.crud.update.one._
import molecule.document.mongodb.setup.TestAsync_mongodb

object UpdateOne_id extends UpdateOne_id with TestAsync_mongodb
object UpdateOne_filter extends UpdateOne_filter with TestAsync_mongodb
object UpdateOne_uniqueAttr extends UpdateOne_uniqueAttr with TestAsync_mongodb
