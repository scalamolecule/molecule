package molecule.sql.mysql.compliance.pagination.cursor.primaryUnique

import molecule.coreTests.spi.pagination.cursor.primaryUnique._
import molecule.sql.mysql.setup.TestAsync_mysql

object Test_Directions extends Directions with TestAsync_mysql
object Test_MutationAdd extends MutationAdd with TestAsync_mysql
object Test_MutationDelete extends MutationDelete with TestAsync_mysql
object Test_Nested extends Nested with TestAsync_mysql
object Test_TypesFilterAttr extends TypesFilterAttr with TestAsync_mysql
