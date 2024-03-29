package molecule.document.mongodb.compliance.filterAttr.one

import molecule.coreTests.spi.filterAttr.one._
import molecule.document.mongodb.setup.TestAsync_mongodb

object Adjacent extends Adjacent with TestAsync_mongodb
object CrossNs extends CrossNs with TestAsync_mongodb
object CrossNsOwned extends CrossNsOwned with TestAsync_mongodb
object FilterAttr_id extends FilterAttr_id with TestAsync_mongodb
object FilterAttrNested extends FilterAttrNested with TestAsync_mongodb
object FilterAttrRef extends FilterAttrRef with TestAsync_mongodb
object Semantics extends Semantics with TestAsync_mongodb
object Sorting extends Sorting with TestAsync_mongodb
object Types extends Types with TestAsync_mongodb
