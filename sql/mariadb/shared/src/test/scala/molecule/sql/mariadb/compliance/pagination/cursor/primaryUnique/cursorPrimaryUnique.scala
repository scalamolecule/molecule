package molecule.sql.mariadb.compliance.pagination.cursor.primaryUnique

import molecule.coreTests.spi.pagination.cursor.primaryUnique._
import molecule.sql.mariadb.setup.Test_mariadb_async

object Test_Directions extends Directions with Test_mariadb_async
object Test_MutationAdd extends MutationAdd with Test_mariadb_async
object Test_MutationDelete extends MutationDelete with Test_mariadb_async
object Test_Nested extends Nested with Test_mariadb_async
object Test_TypesFilterAttr extends TypesFilterAttr with Test_mariadb_async
