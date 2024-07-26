package molecule.sql.sqlite.compliance.relation

import molecule.coreTests.spi.relation.flat.{FlatRef, FlatRefOpt, FlatRefOptNested}
import molecule.sql.sqlite.setup.TestAsync_sqlite

object FlatRefs extends FlatRef with TestAsync_sqlite
object FlatRefOpt extends FlatRefOpt with TestAsync_sqlite
object FlatRefOptNested extends FlatRefOptNested with TestAsync_sqlite