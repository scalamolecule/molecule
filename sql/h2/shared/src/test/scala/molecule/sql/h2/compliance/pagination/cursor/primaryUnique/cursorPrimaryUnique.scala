package molecule.sql.h2.compliance.pagination.cursor.primaryUnique

import molecule.coreTests.compliance.pagination.cursor.primaryUnique._
import molecule.sql.h2.setup.TestAsync_h2

object Directions extends Directions with TestAsync_h2
object MutationAdd extends MutationAdd with TestAsync_h2
object MutationDelete extends MutationDelete with TestAsync_h2
object Nested extends Nested with TestAsync_h2
object TypesFilterAttr extends TypesFilterAttr with TestAsync_h2
