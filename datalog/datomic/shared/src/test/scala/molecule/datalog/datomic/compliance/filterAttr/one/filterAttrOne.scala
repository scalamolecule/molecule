package molecule.datalog.datomic.compliance.filterAttr.one

import molecule.coreTests.compliance.filterAttr.FilterAttr_id
import molecule.coreTests.compliance.filterAttr.one._
import molecule.datalog.datomic.setup.TestAsync_datomic

object Adjacent extends Adjacent with TestAsync_datomic
object CrossNs extends CrossNs with TestAsync_datomic
object Semantics extends Semantics with TestAsync_datomic
object Sorting extends Sorting with TestAsync_datomic
object Types extends Types with TestAsync_datomic

object FilterAttr_id extends FilterAttr_id with TestAsync_datomic
