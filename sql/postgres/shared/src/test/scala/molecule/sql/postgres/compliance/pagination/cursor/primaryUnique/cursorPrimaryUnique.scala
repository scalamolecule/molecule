package molecule.sql.postgres.compliance.pagination.cursor.primaryUnique

import molecule.coreTests.spi.pagination.cursor.primaryUnique._
import molecule.sql.postgres.setup.TestAsync_postgres

object Directions extends Directions with TestAsync_postgres
object MutationAdd extends MutationAdd with TestAsync_postgres
object MutationDelete extends MutationDelete with TestAsync_postgres
object Nested extends Nested with TestAsync_postgres
object TypesFilterAttr extends TypesFilterAttr with TestAsync_postgres
