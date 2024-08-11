package molecule.sql.postgres.compliance.pagination.cursor.primaryUnique

import molecule.coreTests.spi.pagination.cursor.primaryUnique._
import molecule.sql.postgres.setup.TestAsync_postgres

object Test_Directions extends Directions with TestAsync_postgres
object Test_MutationAdd extends MutationAdd with TestAsync_postgres
object Test_MutationDelete extends MutationDelete with TestAsync_postgres
object Test_Nested extends Nested with TestAsync_postgres
object Test_TypesFilterAttr extends TypesFilterAttr with TestAsync_postgres
