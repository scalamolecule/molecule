package molecule.sql.h2.test.filterAttr.one

import molecule.coreTests.test.filterAttr.FilterAttr_id
import molecule.coreTests.test.filterAttr.one._
import molecule.sql.h2.setup.TestAsync_h2

object Adjacent extends Adjacent with TestAsync_h2
object CrossNs extends CrossNs with TestAsync_h2
object Semantics extends Semantics with TestAsync_h2
object Sorting extends Sorting with TestAsync_h2
object Types extends Types with TestAsync_h2

object FilterAttr_id extends FilterAttr_id with TestAsync_h2
