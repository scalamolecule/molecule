package molecule.sql.h2.test.filterAttr.set

import molecule.coreTests.test.filterAttr.set._
import molecule.sql.h2.setup.TestAsync_h2

object Adjacent extends Adjacent with TestAsync_h2
object CrossNs extends CrossNs with TestAsync_h2
object Semantics extends Semantics with TestAsync_h2
object Types extends Types with TestAsync_h2