package molecule.sql.jdbc.test.filterAttr.one

import molecule.coreTests.test.filterAttr.FilterAttr_id
import molecule.coreTests.test.filterAttr.one._
import molecule.sql.jdbc.setup.CoreTestAsync

object Adjacent extends Adjacent with CoreTestAsync
object CrossNs extends CrossNs with CoreTestAsync
object Semantics extends Semantics with CoreTestAsync
object Sorting extends Sorting with CoreTestAsync
object Types extends Types with CoreTestAsync

object FilterAttr_id extends FilterAttr_id with CoreTestAsync
