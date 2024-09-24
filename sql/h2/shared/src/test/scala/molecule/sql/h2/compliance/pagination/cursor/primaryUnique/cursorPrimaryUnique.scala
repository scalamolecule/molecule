package molecule.sql.h2.compliance.pagination.cursor.primaryUnique

import molecule.coreTests.spi.pagination.cursor.primaryUnique._
import molecule.sql.h2.setup.Test_h2_async

object Test_Directions extends Directions with Test_h2_async
object Test_MutationAdd extends MutationAdd with Test_h2_async
object Test_MutationDelete extends MutationDelete with Test_h2_async
object Test_Nested extends Nested with Test_h2_async
object Test_TypesFilterAttr extends TypesFilterAttr with Test_h2_async
