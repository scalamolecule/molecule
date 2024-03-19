package molecule.datalog.datomic.compliance.filterAttr.seq

import molecule.coreTests.spi.filterAttr.seq._
import molecule.datalog.datomic.setup.TestAsync_datomic

object Adjacent extends Adjacent with TestAsync_datomic
object CrossNs extends CrossNs with TestAsync_datomic
object CrossNsOwned extends CrossNsOwned with TestAsync_datomic
object Semantics extends Semantics with TestAsync_datomic
object Types extends Types with TestAsync_datomic
