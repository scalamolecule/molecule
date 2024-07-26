package molecule.sql.mysql.compliance.relation

import molecule.coreTests.spi.relation.flat.{FlatRef, FlatRefOpt, FlatRefOptNested}
import molecule.sql.mysql.setup.TestAsync_mysql

object FlatRefs extends FlatRef with TestAsync_mysql
object FlatRefOpt extends FlatRefOpt with TestAsync_mysql
object FlatRefOptNested extends FlatRefOptNested with TestAsync_mysql
