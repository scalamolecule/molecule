package molecule.sql.sqlite.compliance.pagination.cursor.subUnique

import molecule.coreTests.spi.pagination.cursor.subUnique._
import molecule.sql.sqlite.setup.TestAsync_sqlite

object AttrOrder extends AttrOrder with TestAsync_sqlite
object DirectionsStandardUnique extends DirectionsStandardUnique with TestAsync_sqlite
object DirectionsUniqueStandard extends DirectionsUniqueStandard with TestAsync_sqlite
object MutationAdd extends MutationAdd with TestAsync_sqlite
object MutationDelete extends MutationDelete with TestAsync_sqlite
object Nested extends Nested with TestAsync_sqlite
object OptNested extends OptNested with TestAsync_sqlite
object TypesUniqueValue extends TypesUniqueValue with TestAsync_sqlite
