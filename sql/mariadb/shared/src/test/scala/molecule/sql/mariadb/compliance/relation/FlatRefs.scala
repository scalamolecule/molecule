package molecule.sql.mariadb.compliance.relation

import molecule.coreTests.spi.relation.flat._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object FlatRefs extends FlatRef with TestAsync_mariadb
object FlatRefOpt extends FlatRefOpt with TestAsync_mariadb
object FlatRefOptNested extends FlatRefOptNested with TestAsync_mariadb
object FlatRefOptAdjacent extends FlatRefOptAdjacent with TestAsync_mariadb
