package molecule.document.mongodb.compliance.pagination.cursor.primaryUnique

import molecule.coreTests.spi.pagination.cursor.primaryUnique._
import molecule.document.mongodb.setup.TestAsync_mongodb

object Directions extends Directions with TestAsync_mongodb
object MutationAdd extends MutationAdd with TestAsync_mongodb
object MutationDelete extends MutationDelete with TestAsync_mongodb
object Nested extends Nested with TestAsync_mongodb
object TypesFilterAttr extends TypesFilterAttr with TestAsync_mongodb
