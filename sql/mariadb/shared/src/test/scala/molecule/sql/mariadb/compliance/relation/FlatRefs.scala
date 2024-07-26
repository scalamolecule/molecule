package molecule.sql.mariadb.compliance.relation

import molecule.coreTests.spi.relation.flat.{FlatRef, FlatRefOpt, FlatRefOptNested}
import molecule.sql.mariadb.setup.TestAsync_mariadb

object FlatRefs extends FlatRef with TestAsync_mariadb
object FlatRefOpt extends FlatRefOpt with TestAsync_mariadb
object FlatRefOptNested extends FlatRefOptNested with TestAsync_mariadb
