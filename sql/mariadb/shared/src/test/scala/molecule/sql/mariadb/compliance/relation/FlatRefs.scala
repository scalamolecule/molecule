package molecule.sql.mariadb.compliance.relation

import molecule.coreTests.spi.relation.flat._
import molecule.sql.mariadb.setup.Test_mariadb_async

object Test_FlatRefs extends FlatRef with Test_mariadb_async
object Test_FlatRefOpt extends FlatRefOpt with Test_mariadb_async
object Test_FlatRefOptNested extends FlatRefOptNested with Test_mariadb_async
object Test_FlatRefOptAdjacent extends FlatRefOptAdjacent with Test_mariadb_async
