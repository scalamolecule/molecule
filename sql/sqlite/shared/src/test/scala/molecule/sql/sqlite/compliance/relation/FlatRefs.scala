package molecule.sql.sqlite.compliance.relation

import molecule.coreTests.spi.relation.flat._
import molecule.sql.sqlite.setup.Test_sqlite_async

object Test_FlatRefs extends FlatRef with Test_sqlite_async
object Test_FlatRefOpt extends FlatRefOpt with Test_sqlite_async
object Test_FlatRefOptNested extends FlatRefOptNested with Test_sqlite_async
object Test_FlatRefOptAdjacent extends FlatRefOptAdjacent with Test_sqlite_async