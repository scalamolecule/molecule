package molecule.datalog.datomic.compliance.relation

import molecule.coreTests.spi.relation.flat._
import molecule.datalog.datomic.setup.Test_datomic_async

object Test_FlatRefs extends FlatRef with Test_datomic_async
object Test_FlatRefOpt extends FlatRefOpt with Test_datomic_async
object Test_FlatRefOptNested extends FlatRefOptNested with Test_datomic_async
object Test_FlatRefOptAdjacent extends FlatRefOptAdjacent with Test_datomic_async
