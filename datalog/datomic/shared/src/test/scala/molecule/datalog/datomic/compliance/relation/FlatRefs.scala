package molecule.datalog.datomic.compliance.relation

import molecule.coreTests.spi.relation.flat._
import molecule.datalog.datomic.setup.TestAsync_datomic

object Test_FlatRefs extends FlatRef with TestAsync_datomic
object Test_FlatRefOpt extends FlatRefOpt with TestAsync_datomic
object Test_FlatRefOptNested extends FlatRefOptNested with TestAsync_datomic
object Test_FlatRefOptAdjacent extends FlatRefOptAdjacent with TestAsync_datomic
