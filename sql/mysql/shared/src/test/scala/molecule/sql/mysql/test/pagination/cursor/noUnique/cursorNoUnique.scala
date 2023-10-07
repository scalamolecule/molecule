package molecule.sql.mysql.test.pagination.cursor.noUnique

import molecule.coreTests.test.pagination.cursor.noUnique._
import molecule.sql.mysql.setup.TestAsync_mysql

object AttrOrderMandatory extends AttrOrderMandatory with TestAsync_mysql
object AttrOrderOptional extends AttrOrderOptional with TestAsync_mysql
object DirectionsMandatory extends DirectionsMandatory with TestAsync_mysql
object DirectionsOptional extends DirectionsOptional with TestAsync_mysql
object MutationAdd extends MutationAdd with TestAsync_mysql
object MutationDelete extends MutationDelete with TestAsync_mysql
object Nested extends Nested with TestAsync_mysql
object NestedOpt extends NestedOpt with TestAsync_mysql
object TypesOptional extends TypesOptional with TestAsync_mysql