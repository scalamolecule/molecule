package molecule.sql.sqlite.compliance.relation

import molecule.coreTests.spi.relation.flat._
import molecule.sql.sqlite.setup.TestAsync_sqlite

object FlatRefs extends FlatRef with TestAsync_sqlite
object FlatRefOpt extends FlatRefOpt with TestAsync_sqlite
object FlatRefOptNested extends FlatRefOptNested with TestAsync_sqlite
object FlatRefOptAdjacent extends FlatRefOptAdjacent with TestAsync_sqlite