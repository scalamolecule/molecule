package molecule.sql.sqlite.compliance.pagination.cursor.primaryUnique

import molecule.coreTests.spi.pagination.cursor.primaryUnique._
import molecule.sql.sqlite.setup.TestAsync_sqlite

object Test_Directions extends Directions with TestAsync_sqlite
object Test_MutationAdd extends MutationAdd with TestAsync_sqlite
object Test_MutationDelete extends MutationDelete with TestAsync_sqlite
object Test_Nested extends Nested with TestAsync_sqlite
object Test_TypesFilterAttr extends TypesFilterAttr with TestAsync_sqlite
