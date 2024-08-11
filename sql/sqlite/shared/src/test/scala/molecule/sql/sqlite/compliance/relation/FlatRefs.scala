package molecule.sql.sqlite.compliance.relation

import molecule.coreTests.spi.relation.flat._
import molecule.sql.sqlite.setup.TestAsync_sqlite

object Test_FlatRefs extends FlatRef with TestAsync_sqlite
object Test_FlatRefOpt extends FlatRefOpt with TestAsync_sqlite
object Test_FlatRefOptNested extends FlatRefOptNested with TestAsync_sqlite
object Test_FlatRefOptAdjacent extends FlatRefOptAdjacent with TestAsync_sqlite