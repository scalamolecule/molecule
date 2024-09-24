package molecule.sql.h2.compliance.relation

import molecule.coreTests.spi.relation.flat._
import molecule.sql.h2.setup.Test_h2_async

object Test_FlatRefs extends FlatRef with Test_h2_async
object Test_FlatRefOpt extends FlatRefOpt with Test_h2_async
object Test_FlatRefOptNested extends FlatRefOptNested with Test_h2_async
object Test_FlatRefOptAdjacent extends FlatRefOptAdjacent with Test_h2_async
