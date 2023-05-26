package molecule.datalog.datomic.test.pagination.cursor.noUnique

import molecule.coreTests.test.pagination.cursor.noUnique._
import molecule.datalog.datomic.setup.CoreTestAsync

object AttrOrderMandatory extends AttrOrderMandatory with CoreTestAsync
object AttrOrderOptional extends AttrOrderOptional with CoreTestAsync
object DirectionsMandatory extends DirectionsMandatory with CoreTestAsync
object DirectionsOptional extends DirectionsOptional with CoreTestAsync
object MutationAdd extends MutationAdd with CoreTestAsync
object MutationDelete extends MutationDelete with CoreTestAsync
object Nested extends Nested with CoreTestAsync
object NestedOpt extends NestedOpt with CoreTestAsync
object TypesOptional extends TypesOptional with CoreTestAsync
