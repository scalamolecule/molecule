package molecule.sql.h2.compliance.filterAttr.one

import molecule.coreTests.spi.filterAttr.one._
import molecule.sql.h2.setup.TestAsync_h2

object Adjacent extends Adjacent with TestAsync_h2
object CrossNs extends CrossNs with TestAsync_h2
object CrossNsOwned extends CrossNsOwned with TestAsync_h2
object Semantics extends Semantics with TestAsync_h2
object Sorting extends Sorting with TestAsync_h2
object Types extends Types with TestAsync_h2
