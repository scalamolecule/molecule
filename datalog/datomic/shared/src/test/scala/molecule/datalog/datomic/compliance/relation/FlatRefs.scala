package molecule.datalog.datomic.compliance.relation

import molecule.coreTests.spi.relation.flat._
import molecule.datalog.datomic.setup.TestAsync_datomic

object FlatRefs extends FlatRef with TestAsync_datomic
object FlatRefOpt extends FlatRefOpt with TestAsync_datomic
object FlatRefOptNested extends FlatRefOptNested with TestAsync_datomic
object FlatRefOptAdjacent extends FlatRefOptAdjacent with TestAsync_datomic
