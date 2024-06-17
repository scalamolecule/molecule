package molecule.sql.sqlite.compliance.pagination.cursor.noUnique

import molecule.coreTests.spi.pagination.cursor.noUnique._
import molecule.sql.sqlite.setup.TestAsync_sqlite

object AttrOrderMandatory extends AttrOrderMandatory with TestAsync_sqlite
object AttrOrderOptional extends AttrOrderOptional with TestAsync_sqlite
object DirectionsMandatory extends DirectionsMandatory with TestAsync_sqlite
object DirectionsOptional extends DirectionsOptional with TestAsync_sqlite
object MutationAdd extends MutationAdd with TestAsync_sqlite
object MutationDelete extends MutationDelete with TestAsync_sqlite
object Nested extends Nested with TestAsync_sqlite
object NestedOpt extends NestedOpt with TestAsync_sqlite
object TypesOptional extends TypesOptional with TestAsync_sqlite
