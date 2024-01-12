package molecule.document.mongodb.compliance.filterAttr

import molecule.coreTests.spi.filterAttr._
import molecule.document.mongodb.setup.TestAsync_mongodb

object FilterAttr_id extends FilterAttr_id with TestAsync_mongodb
object FilterAttrNested extends FilterAttrNested with TestAsync_mongodb
object FilterAttrRef extends FilterAttrRef with TestAsync_mongodb
