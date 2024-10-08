package molecule.sql.h2.compliance.pagination.cursor.subUnique

import molecule.coreTests.spi.pagination.cursor.subUnique._
import molecule.sql.h2.setup.Test_h2_async

object Test_AttrOrder extends AttrOrder with Test_h2_async
object Test_DirectionsStandardUnique extends DirectionsStandardUnique with Test_h2_async
object Test_DirectionsUniqueStandard extends DirectionsUniqueStandard with Test_h2_async
object Test_MutationAdd extends MutationAdd with Test_h2_async
object Test_MutationDelete extends MutationDelete with Test_h2_async
object Test_Nested extends Nested with Test_h2_async
object Test_OptNested extends OptNested with Test_h2_async
object Test_TypesUniqueValue extends TypesUniqueValue with Test_h2_async
