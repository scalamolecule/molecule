package molecule.sql.mariadb.compliance.pagination.cursor.primaryUnique

import molecule.coreTests.spi.pagination.cursor.primaryUnique._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object Directions extends Directions with TestAsync_mariadb
object MutationAdd extends MutationAdd with TestAsync_mariadb
object MutationDelete extends MutationDelete with TestAsync_mariadb
object Nested extends Nested with TestAsync_mariadb
object TypesFilterAttr extends TypesFilterAttr with TestAsync_mariadb
