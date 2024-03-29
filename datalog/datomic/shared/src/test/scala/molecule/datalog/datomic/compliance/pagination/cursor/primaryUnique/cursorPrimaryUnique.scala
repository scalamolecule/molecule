package molecule.datalog.datomic.compliance.pagination.cursor.primaryUnique

import molecule.coreTests.spi.pagination.cursor.primaryUnique._
import molecule.datalog.datomic.setup.TestAsync_datomic

object Directions extends Directions with TestAsync_datomic
object MutationAdd extends MutationAdd with TestAsync_datomic
object MutationDelete extends MutationDelete with TestAsync_datomic
object Nested extends Nested with TestAsync_datomic
object TypesFilterAttr extends TypesFilterAttr with TestAsync_datomic
