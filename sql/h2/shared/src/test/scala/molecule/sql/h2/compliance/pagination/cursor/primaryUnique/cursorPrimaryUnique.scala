package molecule.sql.h2.compliance.pagination.cursor.primaryUnique

import molecule.coreTests.spi.pagination.cursor.primaryUnique._
import molecule.sql.h2.setup.TestAsync_h2

object Test_Directions extends Directions with TestAsync_h2
object Test_MutationAdd extends MutationAdd with TestAsync_h2
object Test_MutationDelete extends MutationDelete with TestAsync_h2
object Test_Nested extends Nested with TestAsync_h2
object Test_TypesFilterAttr extends TypesFilterAttr with TestAsync_h2
