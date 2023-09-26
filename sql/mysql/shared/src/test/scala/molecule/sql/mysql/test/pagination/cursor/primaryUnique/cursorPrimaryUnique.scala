package molecule.sql.mysql.test.pagination.cursor.primaryUnique

import molecule.coreTests.test.pagination.cursor.primaryUnique._
import molecule.sql.mysql.setup.TestAsync_mysql

object Directions extends Directions with TestAsync_mysql
object MutationAdd extends MutationAdd with TestAsync_mysql
object MutationDelete extends MutationDelete with TestAsync_mysql
object Nested extends Nested with TestAsync_mysql
object TypesFilterAttr extends TypesFilterAttr with TestAsync_mysql
