package molecule.sql.postgres.compliance.pagination.cursor.subUnique

import molecule.coreTests.spi.pagination.cursor.subUnique._
import molecule.sql.postgres.setup.TestAsync_postgres

object Test_AttrOrder extends AttrOrder with TestAsync_postgres
object Test_DirectionsStandardUnique extends DirectionsStandardUnique with TestAsync_postgres
object Test_DirectionsUniqueStandard extends DirectionsUniqueStandard with TestAsync_postgres
object Test_MutationAdd extends MutationAdd with TestAsync_postgres
object Test_MutationDelete extends MutationDelete with TestAsync_postgres
object Test_Nested extends Nested with TestAsync_postgres
object Test_OptNested extends OptNested with TestAsync_postgres
object Test_TypesUniqueValue extends TypesUniqueValue with TestAsync_postgres
