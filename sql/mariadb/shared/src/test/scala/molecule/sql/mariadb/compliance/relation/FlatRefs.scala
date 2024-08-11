package molecule.sql.mariadb.compliance.relation

import molecule.coreTests.spi.relation.flat._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object Test_FlatRefs extends FlatRef with TestAsync_mariadb
object Test_FlatRefOpt extends FlatRefOpt with TestAsync_mariadb
object Test_FlatRefOptNested extends FlatRefOptNested with TestAsync_mariadb
object Test_FlatRefOptAdjacent extends FlatRefOptAdjacent with TestAsync_mariadb
