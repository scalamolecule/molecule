package molecule.sql.h2.compliance.pagination.cursor.noUnique

import molecule.coreTests.spi.pagination.cursor.noUnique._
import molecule.sql.h2.setup.TestAsync_h2

object AttrOrderMandatory extends AttrOrderMandatory with TestAsync_h2
object AttrOrderOptional extends AttrOrderOptional with TestAsync_h2
object DirectionsMandatory extends DirectionsMandatory with TestAsync_h2
object DirectionsOptional extends DirectionsOptional with TestAsync_h2
object MutationAdd extends MutationAdd with TestAsync_h2
object MutationDelete extends MutationDelete with TestAsync_h2
object Nested extends Nested with TestAsync_h2
object OptNested extends OptNested with TestAsync_h2
object TypesOptional extends TypesOptional with TestAsync_h2
