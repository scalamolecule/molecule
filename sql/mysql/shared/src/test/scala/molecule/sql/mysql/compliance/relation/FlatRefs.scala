package molecule.sql.mysql.compliance.relation

import molecule.coreTests.spi.relation.flat._
import molecule.sql.mysql.setup.TestAsync_mysql

object Test_FlatRefs extends FlatRef with TestAsync_mysql
object Test_FlatRefOpt extends FlatRefOpt with TestAsync_mysql
object Test_FlatRefOptNested extends FlatRefOptNested with TestAsync_mysql
object Test_FlatRefOptAdjacent extends FlatRefOptAdjacent with TestAsync_mysql
