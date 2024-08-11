package molecule.sql.mariadb.compliance.pagination.cursor.primaryUnique

import molecule.coreTests.spi.pagination.cursor.primaryUnique._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object Test_Directions extends Directions with TestAsync_mariadb
object Test_MutationAdd extends MutationAdd with TestAsync_mariadb
object Test_MutationDelete extends MutationDelete with TestAsync_mariadb
object Test_Nested extends Nested with TestAsync_mariadb
object Test_TypesFilterAttr extends TypesFilterAttr with TestAsync_mariadb
