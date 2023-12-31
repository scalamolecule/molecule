package molecule.sql.mariadb.compliance.pagination.cursor.noUnique

import molecule.coreTests.spi.pagination.cursor.noUnique._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object AttrOrderMandatory extends AttrOrderMandatory with TestAsync_mariadb
object AttrOrderOptional extends AttrOrderOptional with TestAsync_mariadb
object DirectionsMandatory extends DirectionsMandatory with TestAsync_mariadb
object DirectionsOptional extends DirectionsOptional with TestAsync_mariadb
object MutationAdd extends MutationAdd with TestAsync_mariadb
object MutationDelete extends MutationDelete with TestAsync_mariadb
object Nested extends Nested with TestAsync_mariadb
object NestedOpt extends NestedOpt with TestAsync_mariadb
object TypesOptional extends TypesOptional with TestAsync_mariadb