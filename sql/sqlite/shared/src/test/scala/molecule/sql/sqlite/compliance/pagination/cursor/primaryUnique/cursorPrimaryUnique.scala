package molecule.sql.sqlite.compliance.pagination.cursor.primaryUnique

import molecule.coreTests.spi.pagination.cursor.primaryUnique._
import molecule.sql.sqlite.setup.TestAsync_sqlite

object Directions extends Directions with TestAsync_sqlite
object MutationAdd extends MutationAdd with TestAsync_sqlite
object MutationDelete extends MutationDelete with TestAsync_sqlite
object Nested extends Nested with TestAsync_sqlite
object TypesFilterAttr extends TypesFilterAttr with TestAsync_sqlite
