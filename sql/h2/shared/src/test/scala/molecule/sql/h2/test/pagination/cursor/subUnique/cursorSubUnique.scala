package molecule.sql.h2.test.pagination.cursor.subUnique

import molecule.coreTests.test.pagination.cursor.subUnique._
import molecule.sql.h2.setup.TestAsync_h2

object AttrOrder extends AttrOrder with TestAsync_h2
object DirectionsStandardUnique extends DirectionsStandardUnique with TestAsync_h2
object DirectionsUniqueStandard extends DirectionsUniqueStandard with TestAsync_h2
object MutationAdd extends MutationAdd with TestAsync_h2
object MutationDelete extends MutationDelete with TestAsync_h2
object Nested extends Nested with TestAsync_h2
object NestedOpt extends NestedOpt with TestAsync_h2
object TypesUniqueValue extends TypesUniqueValue with TestAsync_h2
