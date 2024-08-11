package molecule.sql.h2.compliance.pagination.cursor.noUnique

import molecule.coreTests.spi.pagination.cursor.noUnique._
import molecule.sql.h2.setup.TestAsync_h2

object Test_AttrOrderMandatory extends AttrOrderMandatory with TestAsync_h2
object Test_AttrOrderOptional extends AttrOrderOptional with TestAsync_h2
object Test_DirectionsMandatory extends DirectionsMandatory with TestAsync_h2
object Test_DirectionsOptional extends DirectionsOptional with TestAsync_h2
object Test_MutationAdd extends MutationAdd with TestAsync_h2
object Test_MutationDelete extends MutationDelete with TestAsync_h2
object Test_Nested extends Nested with TestAsync_h2
object Test_OptNested extends OptNested with TestAsync_h2
object Test_TypesOptional extends TypesOptional with TestAsync_h2
