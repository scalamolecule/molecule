package molecule.document.mongodb.compliance.filterAttr.set

import molecule.coreTests.spi.filterAttr.set._
import molecule.document.mongodb.setup.TestAsync_mongodb

object Adjacent extends Adjacent with TestAsync_mongodb
object CrossNs extends CrossNs with TestAsync_mongodb
object CrossNsOwned extends CrossNsOwned with TestAsync_mongodb
object Types extends Types with TestAsync_mongodb
