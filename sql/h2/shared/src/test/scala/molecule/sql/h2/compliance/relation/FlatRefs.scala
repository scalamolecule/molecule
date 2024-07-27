package molecule.sql.h2.compliance.relation

import molecule.coreTests.spi.relation.flat._
import molecule.sql.h2.setup.TestAsync_h2

object FlatRefs extends FlatRef with TestAsync_h2
object FlatRefOpt extends FlatRefOpt with TestAsync_h2
object FlatRefOptNested extends FlatRefOptNested with TestAsync_h2
object FlatRefOptAdjacent extends FlatRefOptAdjacent with TestAsync_h2
