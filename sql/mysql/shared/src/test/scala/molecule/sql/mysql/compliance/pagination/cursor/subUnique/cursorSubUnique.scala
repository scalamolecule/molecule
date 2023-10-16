package molecule.sql.mysql.compliance.pagination.cursor.subUnique

import molecule.coreTests.spi.pagination.cursor.subUnique._
import molecule.sql.mysql.setup.TestAsync_mysql

object AttrOrder extends AttrOrder with TestAsync_mysql
object DirectionsStandardUnique extends DirectionsStandardUnique with TestAsync_mysql
object DirectionsUniqueStandard extends DirectionsUniqueStandard with TestAsync_mysql
object MutationAdd extends MutationAdd with TestAsync_mysql
object MutationDelete extends MutationDelete with TestAsync_mysql
object Nested extends Nested with TestAsync_mysql
object NestedOpt extends NestedOpt with TestAsync_mysql
object TypesUniqueValue extends TypesUniqueValue with TestAsync_mysql
