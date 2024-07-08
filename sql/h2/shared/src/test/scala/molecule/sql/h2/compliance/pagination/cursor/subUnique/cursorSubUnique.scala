package molecule.sql.h2.compliance.pagination.cursor.subUnique

import molecule.coreTests.spi.pagination.cursor.subUnique._
import molecule.sql.h2.setup.TestAsync_h2

object AttrOrder extends AttrOrder with TestAsync_h2
object DirectionsStandardUnique extends DirectionsStandardUnique with TestAsync_h2
object DirectionsUniqueStandard extends DirectionsUniqueStandard with TestAsync_h2
object MutationAdd extends MutationAdd with TestAsync_h2
object MutationDelete extends MutationDelete with TestAsync_h2
object Nested extends Nested with TestAsync_h2
object OptNested extends OptNested with TestAsync_h2
object TypesUniqueValue extends TypesUniqueValue with TestAsync_h2
