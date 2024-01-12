package molecule.document.mongodb.compliance.filter

import molecule.coreTests.spi.filter._
import molecule.document.mongodb.setup.TestAsync_mongodb

object FilterOne_id extends FilterOne_id with TestAsync_mongodb
object FilterRef extends FilterRef with TestAsync_mongodb

