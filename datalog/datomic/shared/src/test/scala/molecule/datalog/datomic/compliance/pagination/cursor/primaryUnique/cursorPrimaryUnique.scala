package molecule.datalog.datomic.compliance.pagination.cursor.primaryUnique

import molecule.coreTests.spi.pagination.cursor.primaryUnique._
import molecule.datalog.datomic.setup.Test_datomic_async

object Test_Directions extends Directions with Test_datomic_async
object Test_MutationAdd extends MutationAdd with Test_datomic_async
object Test_MutationDelete extends MutationDelete with Test_datomic_async
object Nested extends Nested with Test_datomic_async
object TypesFilterAttr extends TypesFilterAttr with Test_datomic_async
