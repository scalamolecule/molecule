package molecule.sql.postgres.test.pagination.cursor.subUnique

import molecule.coreTests.test.pagination.cursor.subUnique._
import molecule.sql.postgres.setup.TestAsync_postgres

object AttrOrder extends AttrOrder with TestAsync_postgres
object DirectionsStandardUnique extends DirectionsStandardUnique with TestAsync_postgres
object DirectionsUniqueStandard extends DirectionsUniqueStandard with TestAsync_postgres
object MutationAdd extends MutationAdd with TestAsync_postgres
object MutationDelete extends MutationDelete with TestAsync_postgres
object Nested extends Nested with TestAsync_postgres
object NestedOpt extends NestedOpt with TestAsync_postgres
object TypesUniqueValue extends TypesUniqueValue with TestAsync_postgres
