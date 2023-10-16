package molecule.datalog.datomic.compliance.filterAttr.set

import molecule.coreTests.spi.filterAttr.set._
import molecule.datalog.datomic.setup.TestAsync_datomic

object Adjacent extends Adjacent with TestAsync_datomic
object CrossNs extends CrossNs with TestAsync_datomic
object Semantics extends Semantics with TestAsync_datomic
object Types extends Types with TestAsync_datomic
