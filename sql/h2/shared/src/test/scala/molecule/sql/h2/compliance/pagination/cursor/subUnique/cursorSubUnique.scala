package molecule.sql.h2.compliance.pagination.cursor.subUnique

import molecule.coreTests.spi.pagination.cursor.subUnique._
import molecule.sql.h2.setup.TestAsync_h2

object Test_AttrOrder extends AttrOrder with TestAsync_h2
object Test_DirectionsStandardUnique extends DirectionsStandardUnique with TestAsync_h2
object Test_DirectionsUniqueStandard extends DirectionsUniqueStandard with TestAsync_h2
object Test_MutationAdd extends MutationAdd with TestAsync_h2
object Test_MutationDelete extends MutationDelete with TestAsync_h2
object Test_Nested extends Nested with TestAsync_h2
object Test_OptNested extends OptNested with TestAsync_h2
object Test_TypesUniqueValue extends TypesUniqueValue with TestAsync_h2
